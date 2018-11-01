package com.liang.funny.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String email;

    private String telephone;

    private Boolean isAdmin;

    private Boolean status;

    private Date updatedTime;

    private Date createdTime;

    public User(Integer id, String name, String email, String telephone, Boolean isAdmin, Boolean status, Date updatedTime, Date createdTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.telephone = telephone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
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