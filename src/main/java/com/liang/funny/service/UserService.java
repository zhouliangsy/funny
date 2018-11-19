package com.liang.funny.service;

import com.liang.funny.model.Role;
import com.liang.funny.model.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    /**
     * https://blog.csdn.net/wanderlustLee/article/details/79832597
     * @CachePut 是将数据加入到redis缓存中
     *
     * @Cacheable 在获取数据的时候会先查询缓存，如果缓存中存在，则不执行查询数据库的方法，如果不存在则查询数据库，并加入到缓存中。
     *
     * @CacheEvict 一般注解到删除数据的操作上，会将一条或多条数据从缓存中删除。
     *
     * 这三个方法都有value 和 key属性。
     *
     * value指的是缓存的名称，不能为空。也可以在类@CacheConfig注解上指定value，则后面的方法的注解value可省略且value值与类注解的value相同。
     *
     * key是缓存的键，默认为空，既表示使用方法的参数类型及参数值作为key。可通过key = "#p0",p0为方法的第一个参数，p1为第二个，也可直接 #参数的名字。
     *
     * 还有一个注解为@Caching  用于组合将多个注解注解到一个方法上
     *
     */

    //增加用户
    public int addUser(Map<String, Object> map);


    //删除用户
    @CacheEvict(key="'user_'+#id", value="users", condition="#id!=1")
    public int delUser(Integer id);


    //更新用户信息
    public boolean updateUser(Integer id, Map<String, Object> map);


    //根据id获取用户信息
    @Cacheable(key="'user_'+#id",value="'user'+#id")
    public User getUser(int id);

    //根据username获取用户信息
    public User findUserByName(String name);

    //获取所有用户信息
    public List<User> getUsers();

    public Set<Role> getRolesByUserId(int id);
}
