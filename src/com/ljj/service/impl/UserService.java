package com.ljj.service.impl;

import com.ljj.dao.impl.UserDao;
import com.ljj.entity.User;
import com.ljj.service.IUserService;
import com.ljj.util.Scan;

import java.util.ArrayList;
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
    public void ban() {
        System.out.println("输入用户的id");
        if (scan.hasNextInt()) {
            if (dao.ban(scan.nextInt())) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        }
    }

    @Override
    public boolean getAuthority() {
        return user.flag;
    }

    @Override
    public void delete() {
        System.out.println("输入要删除的用户的id");
        if (scan.hasNextInt()) {
            if (dao.delete(scan.nextInt())) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        }
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public void getActivate() {
        ArrayList<String[]> res = dao.getActivate();
        System.out.println("活跃用户------------------");
        for (int i = 0; i < res.size(); i++) {
            String[] temp = res.get(i);
            System.out.println(temp[0] + "\t\t\t" + temp[1]);
        }
        System.out.println("--------------------------");
    }

    @Override
    public void list() {
        ArrayList<User> res = dao.getAll();
        System.out.println("所有用户------------------");
        for (int i = 0; i < res.size(); i++) {
            User temp = res.get(i);
            System.out.println(temp.getId() + "\t\t\t" + temp.getName() + "\t\t\t" + (temp.state ? "被禁用" : "未禁用") + "\t\t\t" + (temp.flag ? "管理员" : "普通用户"));
        }
        System.out.println("--------------------------");
    }

    @Override
    public void set() {
        System.out.println("输入用户的id");
        if (scan.hasNextInt()) {
            if (dao.set(scan.nextInt())) {
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }
        }
    }
}
