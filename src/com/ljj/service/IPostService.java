package com.ljj.service;

public interface IPostService {
    void listAll();

    public void create(int userId,int blockId);

    public void delete();

    boolean into();

    void listMy();
    boolean listOne(int id);

    void listReplyed();
}
