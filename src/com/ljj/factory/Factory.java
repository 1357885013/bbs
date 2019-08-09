package com.ljj.factory;

import com.ljj.exception.CanFindDbConfig;
import com.ljj.exception.CloseDbResourceError;
import com.ljj.exception.ConnectToDbError;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Factory {
    private static Properties pro;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        pro = new Properties();
        try {
            pro.load(new FileInputStream("./db.properties"));
        } catch (IOException e) {
            System.out.println("找不到db.properties 或者次文件内容出错！程序正在退出...");
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

    public Factory() {
    }

    public static Connection getCon() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bbs", pro);
        } catch (SQLException e) {
            throw new ConnectToDbError(e.getMessage());
        }
        return con;
    }

    public static void closeAll(ResultSet res, Statement state, Connection con) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                throw new CloseDbResourceError("when close ResultSet:" + e.getMessage());
            }
        }
        try {
            state.close();
        } catch (SQLException e) {
            throw new CloseDbResourceError("when close StateMent:" + e.getMessage());
        }
        try {
            con.close();
        } catch (SQLException e) {
            throw new CloseDbResourceError("when close Connection:" + e.getMessage());
        }
    }
}
