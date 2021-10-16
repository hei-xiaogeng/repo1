package com.gmc.lvyou.dao.impl;

import com.gmc.lvyou.dao.SellerDao;
import com.gmc.lvyou.domain.RouteImg;
import com.gmc.lvyou.domain.Seller;
import com.gmc.lvyou.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoimpl implements SellerDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findById(int id) {
        String sql="select * from tab_seller where sid=?";

        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);
    }
}
