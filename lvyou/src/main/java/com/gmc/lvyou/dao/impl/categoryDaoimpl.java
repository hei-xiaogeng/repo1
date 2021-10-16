package com.gmc.lvyou.dao.impl;

import com.gmc.lvyou.dao.categoryDao;
import com.gmc.lvyou.domain.Category;
import com.gmc.lvyou.service.CategoryService;
import com.gmc.lvyou.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class categoryDaoimpl implements categoryDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category ";
        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
