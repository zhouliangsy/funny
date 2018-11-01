package com.liang.funny.dao;

import com.liang.funny.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> getUsers();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}