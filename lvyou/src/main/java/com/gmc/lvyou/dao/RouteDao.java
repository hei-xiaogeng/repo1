package com.gmc.lvyou.dao;

import com.gmc.lvyou.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 根据cid 查询总记录数
     *
     */

    public int findTotalCound(int cid,String rname);

    /**
     * 根据cid start，pageSize查询当前页的数据集合
     */
    public List<Route> findByPage(int cid , int start , int pageSize, String rname);


    /**
     * 根据id查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
