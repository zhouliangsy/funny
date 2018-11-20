package com.liang.funny.service;

import com.liang.funny.dao.UserMapper;
import com.liang.funny.model.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户操作实现类
 *
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    public int addUser(Map<String, Object> map) {
        String name = map.get("name").toString();
        String password = "Abc1234!";
        Boolean isAdmin = Boolean.parseBoolean(map.get("isAdmin").toString());
        Boolean status = Boolean.parseBoolean(map.get("status").toString());

        User user = new User();
        user.setName(name);
        user.setSalt(name);
        user.setPassword(new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, ByteSource.Util.bytes(user.getSalt()), 1024).toString());

        user.setIsAdmin(isAdmin);
        user.setStatus(status);
        return userMapper.insert(user);
    }

    @Override
    public int delUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public boolean updateUser(Integer id, Map<String, Object> map) {
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        Boolean isAdmin = Boolean.parseBoolean(map.get("isAdmin").toString());
        Boolean status = Boolean.parseBoolean(map.get("status").toString());
        User user = userMapper.selectByPrimaryKey(id);

        user.setName(name);
        user.setSalt(name);
        user.setPassword(new SimpleHash(Sha256Hash.ALGORITHM_NAME, password, ByteSource.Util.bytes(user.getSalt()), 1024).toString());
        user.setIsAdmin(isAdmin);
        user.setStatus(status);
        user.setUpdatedTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return true;
    }

    @Override
    public User getUser(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

}
