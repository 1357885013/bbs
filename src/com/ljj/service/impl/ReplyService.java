package com.ljj.service.impl;

import com.ljj.dao.impl.ReplyDao;
import com.ljj.entity.Reply;
import com.ljj.service.IReplyService;
import com.ljj.util.Scan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ReplyService implements IReplyService {
    private Scanner scan = Scan.scan;
    private Reply reply = new Reply();
    private ReplyDao dao = new ReplyDao();

    @Override
    public void refresh(int postId) {
        ArrayList<Reply> res = dao.getAll(postId);
        Object[] blocks = res.toArray();
        for (Object o : blocks) {
            Reply reply = (Reply) o;
            System.out.println(reply.getId() + "\t\t\t\t" + reply.getContext() + "\t\t\t\t" + reply.getFormatTime());
        }
        System.out.println("~~~~~~~~~~~~~~~~帖子结束~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public void reply(int userId, int blockId, int postId) {
        String temp = scan.nextLine();
        if (!temp.equals(""))
            reply.setContext(temp);
        else {
            System.out.println("please input content:");
            if (scan.hasNextLine())
                reply.setContext(scan.nextLine());
        }

        reply.setUserId(userId);
        reply.setPostId(postId);
        reply.setTime(new Date());
        try {
            if (dao.add(reply)) {
                System.out.println(">>>>>>>>>创建成功<<<<<<<<");
            } else {
                System.out.println(">>>>>>>>>创建失败<<<<<<<<");
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState() + "   " + e.getMessage());
            e.printStackTrace();
            System.out.println(">>>>>>>>>创建失败，已存在同名论坛或其它原因<<<<<<<<");
        }
    }

    @Override
    public void delete(int postId, int userId, boolean state) {
        System.out.println("请输入 id");
        if (scan.hasNextInt()) {
            int id = scan.nextInt();
            if (!state) {
                if (userId != dao.getUserId(id)) {
                    System.out.println("失败，权限不足，你只能删除自己的内容");
                    return;
                }
            }
            if (dao.delete(id, postId, userId, state))
                System.out.println("删除成功");
            else
                System.out.println("删除失败，不存在此id或其它原因");
        }
    }

    @Override
    public boolean into() {
        return false;
    }

    @Override
    public void listMy() {

    }

    @Override
    public void listReplyed() {

    }


}
