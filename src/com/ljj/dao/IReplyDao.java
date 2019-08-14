package com.ljj.dao;

import com.ljj.entity.Reply;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IReplyDao {
    public ArrayList<Reply> getAll(int postId);

    boolean add(Reply reply) throws SQLException;

    public String getNameById(int id);

    public int getIdByTitle(String name);

    public boolean delete(int replyId, int postId, int userId, boolean isAdmin);

    boolean getPostById(Reply post);

    int getUserId(int id);
}
