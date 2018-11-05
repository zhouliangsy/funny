package com.liang.funny.service;

import com.liang.funny.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    //增加角色
    public int addRole(Map<String, Object> map);
    //删除角色
    public int delRole(Integer id);
    //更新角色信息
    public int updateRole(Integer id, Map<String, Object> map);
    //根据id获取角色信息
    public Role getRole(int id);
    //获取所有角色信息
    public List<Role> getRoles();
}
