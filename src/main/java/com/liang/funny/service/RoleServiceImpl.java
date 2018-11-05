package com.liang.funny.service;

import com.liang.funny.dao.RoleMapper;
import com.liang.funny.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int addRole(Map<String, Object> map) {
        String name = map.get("name").toString();
        Boolean status = Boolean.parseBoolean(map.get("status").toString());
        Role role = new Role();
        role.setName(name);
        role.setStatus(status);
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        return roleMapper.insert(role);
    }

    @Override
    public int delRole(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Integer id, Map<String, Object> map) {
        String name = map.get("name").toString();
        Boolean status = Boolean.parseBoolean(map.get("status").toString());

        Role role = roleMapper.selectByPrimaryKey(id);
        role.setName(name);
        role.setStatus(status);
        role.setUpdatedTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role getRole(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }
}
