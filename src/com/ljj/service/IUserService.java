package com.ljj.service;

public interface IUserService {
    public void register();

    public boolean login();

    public void ban();

    public boolean getAuthority();

    public void delete();

    boolean update();

    void getActivate();

    void list();

    void set();
}