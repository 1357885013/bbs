package com.ljj.service;

public interface IUserService {
    public void register();

    public boolean login();

    public boolean ban();

    public boolean getAuthority();

    public boolean delete();

    boolean update();
}
