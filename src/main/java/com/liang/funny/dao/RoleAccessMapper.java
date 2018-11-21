package com.liang.funny.dao;

import com.liang.funny.model.RoleAccess;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleAccessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleAccess record);

    int insertSelective(RoleAccess record);

    RoleAccess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleAccess record);

    int updateByPrimaryKey(RoleAccess record);
}