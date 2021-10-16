package com.gmc.lvyou.dao.impl;

import com.gmc.lvyou.dao.RouteImgDao;
import com.gmc.lvyou.domain.RouteImg;
import com.gmc.lvyou.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoimpl implements RouteImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql="select * from tab_route_img where rid=?";

        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
