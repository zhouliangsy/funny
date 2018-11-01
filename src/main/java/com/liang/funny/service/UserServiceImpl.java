package com.liang.funny.service;

import com.liang.funny.dao.UserMapper;
import com.liang.funny.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean delUser(Integer id) {
        return false;
    }

//    @Override
//    public boolean updateUser(User user) {
//        return false;
//    }

    @Override
    public boolean updateUser(Integer id, Map<String, Object> map) {
        return false;
    }

    @Override
    public User getUser(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }
}
