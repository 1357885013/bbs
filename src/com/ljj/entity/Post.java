package com.ljj.entity;

import com.ljj.util.MyTime;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String context;
    private Date Time;
    private int userId;
    private int blockId;

    public Post() {
    }

    public Post(int id, String title, Date time) {
        this.id = id;
        this.title = title;
        Time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTime() {
        return Time;
    }
    public String getFormatTime() {
        return MyTime.formateTime(Time);
    }

    public void setTime(Date time) {
        Time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }
}
