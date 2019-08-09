package com.ljj.service.impl;

import com.ljj.dao.impl.UserDao;
import com.ljj.entity.User;
import com.ljj.service.IUserService;
import com.ljj.util.Scan;

import java.util.Scanner;

public class UserService implements IUserService {
    public User user = new User();
    private Scanner scan = Scan.scan;
    private UserDao dao = new UserDao();

    @Override
    public void register() {
        User user = new User();
        System.out.println("please input your name:");
        if (scan.hasNext())
            user.setName(scan.next());
        System.out.println("please input your password:");
        if (scan.hasNext())
            user.setPassword(scan.next());

        switch (dao.register(user)) {
            case 1:
                System.out.println("regist succed");
                break;
            case 0:
                System.out.println("regist failed");
                break;
            case -1:
                System.out.println("this name is already be used!");
                break;
        }
    }

    @Override
    public boolean login() {
        System.out.println("please input your name:");
        if (scan.hasNext())
            user.setName(scan.next());
        System.out.println("please input your password:");
        if (scan.hasNext())
            user.setPassword(scan.next());
        int uId;
        switch (uId = dao.login(user)) {
            case 1:
                System.out.println("login succed,Welcome to LJJ BBS " + user.getName());
                return true;
            case 0:
                System.out.println("login failed");
                break;
            case -1:
                System.out.println("user name error!");
                break;
            case -2:
                System.out.println("password error!");
                break;
        }
        return false;
    }

    @Override
    public boolean ban() {
        return false;
    }

    @Override
    public boolean getAuthority() {
        return user.flag;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
