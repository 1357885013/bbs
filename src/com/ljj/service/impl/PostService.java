package com.ljj.service.impl;

import com.ljj.dao.impl.PostDao;
import com.ljj.entity.Post;
import com.ljj.service.IPostService;
import com.ljj.util.Scan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PostService implements IPostService {
    public Post post = new Post();
    private PostDao dao = new PostDao();
    private Scanner scan = Scan.scan;

    @Override
    public void listAll() {
        ArrayList<Post> res = dao.getAll();
        Object[] blocks = res.toArray();
        System.out.println("~~~~~~~~~~~~~~~~所有帖子~~~~~~~~~~~~~~~~~~~~");
        if (blocks.length == 0)
            System.out.println("暂无帖子");
        for (Object o : blocks) {
            Post post = (Post) o;
            System.out.println(post.getId() + "\t" + post.getTitle() + "\t" + post.getFormatTime());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~结束~~~~~~~~~~~~~~~~~~~~");

    }

    @Override
    public void listMy() {

    }

    @Override
    public boolean listOne(int id) {
        Post p = new Post();
        p.setId(id);
        if (dao.getPostById(p)) {
            System.out.println("~~~~~~~~~~~~~~~~帖子标题~~~~~~~~~~~~~~~~~~~~");
            System.out.println("              《" + p.getTitle() + "》");
            System.out.println("~~~~~~~~~~~~~~~~~~内容~~~~~~~~~~~~~~~~~~~~");
            System.out.println(p.getContext());
            System.out.println("~~~~~~~~~~~~~~~~~~回复~~~~~~~~~~~~~~~~~~~~");
            return true;
        }
        return false;
    }

    @Override
    public void listReplyed() {

    }

    @Override
    public boolean into() {
        if (scan.hasNextInt()) {
            post.setId(scan.nextInt());
            if (dao.getPostById(post)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void create(int userId, int blockId) {
        String temp = scan.nextLine();
        if (!temp.equals(""))
            post.setTitle(temp);
        else {
            System.out.println("please input a title:");
            if (scan.hasNextLine())
                post.setTitle(scan.nextLine());
        }

        System.out.println("please input content:");
        if (scan.hasNextLine())
            post.setContext(scan.nextLine());
        post.setUserId(userId);
        post.setBlockId(blockId);
        post.setTime(new Date());
        try {
            if (dao.create(post)) {
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
    public void delete() {
        System.out.println("请输入帖子id:");
        if (scan.hasNextInt())
            if (dao.delete((scan.nextInt())))
                System.out.println(">>>>>>>>>删除成功<<<<<<<<");
            else
                System.out.println(">>>>>>>>>删除失败，不存在次id或其它原因<<<<<<<<");
    }


}
