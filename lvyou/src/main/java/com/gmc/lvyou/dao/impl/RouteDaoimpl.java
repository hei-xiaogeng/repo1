package com.gmc.lvyou.dao.impl;

import com.gmc.lvyou.dao.RouteDao;
import com.gmc.lvyou.domain.Route;
import com.gmc.lvyou.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoimpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid 查询总记录数
     */
    @Override
    public int findTotalCound(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();//条件们
        //2判断参数是否有值
        if (cid != 0) {
            sb.append("  and cid= ? ");
            params.add(cid);//添加对应的值

        }
        if (rname != null && rname.length() > 0 && !"null".equals(rname)) {
            sb.append("  and rname like ?  ");

            params.add("%" + rname + "%");

        }
        sql = sb.toString();
//        System.out.println(cid);
//        System.out.println(rname);
//        System.out.println(template.queryForObject(sql, Integer.class, params.toArray()));

        return template.queryForObject(sql, Integer.class, params.toArray());

    }

    /**
     * 根据cid 起始start，pageSize查询当前页的数据集合
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {


        //String sql = "select * from tab_route where cid = ? and rname like ?  limit ? , ?";
        String sql = " select * from tab_route where 1 = 1 ";
        //1.定义sql模板
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if (rname != null && rname.length() > 0&& !"null".equals(rname)) {
            sb.append(" and rname like ? ");

            params.add("%" + rname + "%");
        }
        sb.append(" limit ? , ? ");//分页条件

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);


        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    /**
     * 根据id查询
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }


}
