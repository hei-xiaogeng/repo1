package com.gmc.lvyou.dao;

import com.gmc.lvyou.domain.Category;

import java.util.List;

public interface categoryDao {
    /**
     * 查找分类
     * @return
     */
    List<Category> findAll();
}
