package com.liang.funny.model;

import java.io.Serializable;
import java.util.Date;

public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String title;

    private String urls;

    private Boolean status;

    private Date updatedTime;

    private Date createdTime;

    public Access(Integer id, String title, String urls, Boolean status, Date updatedTime, Date createdTime) {
        this.id = id;
        this.title = title;
        this.urls = urls;
        this.status = status;
        this.updatedTime = updatedTime;
        this.createdTime = createdTime;
    }

    public Access() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls == null ? null : urls.trim();
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