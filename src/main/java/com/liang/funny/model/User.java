package com.liang.funny.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String salt;

    private Boolean isAdmin;

    private Boolean status;

    private Date updatedTime;

    private Date createdTime;

    public User(Integer id, String name, String password, String salt, Boolean isAdmin, Boolean status, Date updatedTime, Date createdTime) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.isAdmin = isAdmin;
        this.status = status;
        this.updatedTime = updatedTime;
        this.createdTime = createdTime;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
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