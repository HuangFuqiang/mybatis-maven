package com.code.mybatismaven;

import com.code.mybatismaven.annotation.CreateTime;

import java.util.Date;

public class User {

    private Long id;

    private String name;

    @CreateTime
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
