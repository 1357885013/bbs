package com.ljj.dao.impl;

import com.ljj.dao.IUserDao;
import com.ljj.entity.User;
import com.ljj.factory.Factory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao {

    @Override
    public int register(User user) {
        Connection con = Factory.getCon();
        PreparedStatement state = null;
        if (hasUser(user.getName(), con))
            return -1;
        try {
            state = con.prepareStatement("insert into user values(null,?,?,?,?)");
            state.setString(1, user.getName());
            state.setString(2, user.getPassword());
            state.setBoolean(3, user.state);
            state.setBoolean(4, user.flag);

            if (state.executeUpdate() >= 1)
                return 1;
            else
                return 0;
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        } finally {
            Factory.closeAll(null, state, con);
        }
        return 0;
    }

    @Override
    public int login(User user) {
        Connection con = Factory.getCon();
        PreparedStatement state = null;
        ResultSet res = null;
        if (!hasUser(user.getName(), con))
            return -1;
        //TODO 判断是否被禁用
        try {
            state = con.prepareStatement("select uid,state,flag from user where uName=? and uPass = ?");
            state.setString(1, user.getName());
            state.setString(2, user.getPassword());

            res = state.executeQuery();
            if (res.next()) {
                user.setId(res.getInt(1));
                user.state = (res.getBoolean(2));
                user.flag = (res.getBoolean(3));
                return 1;
            } else
                return -2;
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        } finally {
            Factory.closeAll(res, state, con);
        }
        return 0;
    }

    @Override
    public boolean hasUser(String name, Connection con) {
        PreparedStatement state = null;
        ResultSet res = null;
        try {
            state = con.prepareStatement("select * from user where uName = ?");
            state.setString(1, name);
            res = state.executeQuery();

            return res.next();
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean ban(String time) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(User user) {
        Connection con = Factory.getCon();
        PreparedStatement state = null;
        if (hasUser(user.getName(), con))
            return false;
        try {
            state = con.prepareStatement("insert into user values(null,?,?,?,?)");
            state.setString(1, user.getName());
            state.setString(2, user.getPassword());
            state.setBoolean(3, user.state);
            state.setBoolean(4, user.flag);

            return (state.executeUpdate() >= 1);

        } catch (SQLException e) {
            System.out.println(e.getSQLState() + e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        } finally {
            Factory.closeAll(null, state, con);
        }
        return false;
    }
}
