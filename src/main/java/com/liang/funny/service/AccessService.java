package com.liang.funny.service;

import com.liang.funny.model.Access;

import java.util.List;
import java.util.Map;

public interface AccessService {
    //增加权限
    public int addAccess(Map<String, Object> map);
    //删除权限
    public int delAccess(Integer id);
    //更新权限信息
    public int updateAccess(Integer id, Map<String, Object> map);
    //根据id获取权限信息
    public Access getAccess(int id);
    //获取所有权限信息
    public List<Access> getAccesss();
}
