package com.gmc.lvyou.service.impl;

import com.gmc.lvyou.dao.categoryDao;
import com.gmc.lvyou.dao.impl.categoryDaoimpl;
import com.gmc.lvyou.domain.Category;
import com.gmc.lvyou.service.CategoryService;
import com.gmc.lvyou.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceimpl implements CategoryService {

    categoryDao categoryDao=new categoryDaoimpl();
    @Override
    public List<Category> findAll() {


        List<Category> cs = new ArrayList<Category>();

        cs = categoryDao.findAll();




        return cs;
    }
}
