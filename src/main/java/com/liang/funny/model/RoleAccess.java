package com.liang.funny.model;

import java.util.Date;

public class RoleAccess {
    private Integer id;

    private Integer roleId;

    private Integer accessId;

    private Date createdTime;

    public RoleAccess(Integer id, Integer roleId, Integer accessId, Date createdTime) {
        this.id = id;
        this.roleId = roleId;
        this.accessId = accessId;
        this.createdTime = createdTime;
    }

    public RoleAccess() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}