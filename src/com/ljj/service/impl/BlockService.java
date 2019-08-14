package com.ljj.service.impl;

import com.ljj.dao.impl.BlockDao;
import com.ljj.entity.Block;
import com.ljj.service.IBlockService;
import com.ljj.util.Scan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BlockService implements IBlockService {
    public Block block = new Block();
    private BlockDao dao = new BlockDao();
    private Scanner scan = Scan.scan;

    @Override
    public void listAll() {
        ArrayList<Block> res = dao.getAllBlocks();
        Object[] blocks = res.toArray();
        System.out.println("~~~~~~~~~~~~~~~~~~版块~~~~~~~~~~~~~~~~~~~~");
        if(blocks.length==0)
            System.out.println("暂无版块");
        for (Object o : blocks) {
            Block block = (Block) o;
            System.out.println(block.getId() + "\t" + block.getName());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~结束~~~~~~~~~~~~~~~~~~~~");

    }

    @Override
    public boolean into() {
        if (scan.hasNextInt()) {
            block.setId(scan.nextInt());
            String name = dao.getNameById(block.getId());
            if (name != null) {
                block.setName(name);
                return true;
            }
        }
        return false;
    }

    @Override
    public void create() {
        String temp = scan.nextLine();
        if (!temp.trim().equals(""))
            block.setName(temp);
        else {
            System.out.println("请输入版块名称:");
            if (scan.hasNextLine())
                block.setName(scan.nextLine());
        }

        try {
            if (dao.create(block)) {
                System.out.println(">>>>>>>>>创建成功<<<<<<<<");
            } else {
                System.out.println(">>>>>>>>>创建失败<<<<<<<<");
            }
        } catch (SQLException e) {
            System.out.println(">>>>>>>>>创建失败，已存在同名论坛或其它原因<<<<<<<<");
        }
    }

    @Override
    public void delete() {
        System.out.println("请输入版块id:");
        if (scan.hasNextInt())
            if (dao.delete((scan.nextInt())))
                System.out.println(">>>>>>>>>删除成功<<<<<<<<");
            else
                System.out.println(">>>>>>>>>删除失败，不存在次id或其它原因<<<<<<<<");
    }
}
