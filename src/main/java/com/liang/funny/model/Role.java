package com.liang.funny.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Boolean status;

    private Date updatedTime;

    private Date createdTime;

    private Set<Access> Accesses = new HashSet<>();

    private Set<User> users = new HashSet<>();

    public Set<Access> getAccesses() {
        return Accesses;
    }

    public void setAccesses(Set<Access> accesses) {
        Accesses = accesses;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role(Integer id, String name, Boolean status, Date updatedTime, Date createdTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.updatedTime = updatedTime;
        this.createdTime = createdTime;
    }

    public Role() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}