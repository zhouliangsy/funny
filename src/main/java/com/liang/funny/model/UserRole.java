package com.liang.funny.model;

import java.util.Date;

public class UserRole {
    private Integer id;

    private Integer uid;

    private Integer roleId;

    private Date createdTime;

    public UserRole(Integer id, Integer uid, Integer roleId, Date createdTime) {
        this.id = id;
        this.uid = uid;
        this.roleId = roleId;
        this.createdTime = createdTime;
    }

    public UserRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}