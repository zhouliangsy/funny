package com.liang.funny.service;

import com.liang.funny.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //增加用户
    public int addUser(Map<String, Object> map);
    //删除用户
    public int delUser(Integer id);
    //更新用户信息
    public boolean updateUser(Integer id, Map<String, Object> map);
    //根据id获取用户信息
    public User getUser(int id);
    //获取所有用户信息
    public List<User> getUsers();
}
