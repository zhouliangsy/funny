package com.liang.funny.util.shiro;

import com.liang.funny.model.Access;
import com.liang.funny.model.Role;
import com.liang.funny.model.User;
import com.liang.funny.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 这个类是参照JDBCRealm写的，主要是自定义了如何查询用户信息，如何查询用户的角色和权限，如何校验密码等逻辑
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 详细可以参考https://github.com/xlui/Spring-Boot-Examples/tree/master/spring-boot-shiro 关于加密的两种方式
     * 告诉shiro如何根据获取到的用户信息中的密码和盐值来校验密码
     */
    {
        //设置用于匹配密码的CredentialsMatcher
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);//此处有多种选择 Md2Hash Md5Hash Sha1Hash Sha256Hash Sha384Hash Sha512Hash SimpleHash
        hashMatcher.setStoredCredentialsHexEncoded(false);
        hashMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 Sha256Hash.ALGORITHM_NAME(Sha256Hash.ALGORITHM_NAME(""));
        this.setCredentialsMatcher(hashMatcher);
    }


    /**
     * 授权
     * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("获取角色信息："+user.getRoles());

        if (user.getRoles().size() > 0) {
            Set<String> roleIds = new HashSet<String>();
            for (Role role : user.getRoles()) {
                roleIds.add(role.getId().toString());
                if (role.getAccesses().size() > 0) {
                    Set<String> handleMappings = new HashSet<String>();
                    for (Access authority : role.getAccesses())
                        handleMappings.add(authority.getTitle());
                    info.setStringPermissions(handleMappings);
                }
            }
            info.setRoles(roleIds);
        }
        return info;
    }

    /**
     * 认证
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        String username = (String) token.getPrincipal();

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.findUserByName(username);
        if (userInfo == null) {
            throw new UnknownAccountException("No account found for admin [" + username + "]");
        }
        if(userInfo.getStatus() == false) { //账户冻结
            throw new LockedAccountException();
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
//        Set<String> roles = new HashSet<String>();
//        for (Role role : userInfo.getRoles()) {
//            roles.add(role.getId().toString());
//            if (role.getAccesses().size() > 0) {
//                Set<String> accesses = new HashSet<String>();
//                for (Access authority : role.getAccesses())
//                    accesses.add(authority.getTitle());
//            }
//        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), getName());
        if (userInfo.getSalt() != null) {
            authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userInfo.getSalt()));
        }
        return authenticationInfo;

    }

}
