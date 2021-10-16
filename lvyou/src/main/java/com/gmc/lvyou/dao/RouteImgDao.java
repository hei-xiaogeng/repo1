package com.gmc.lvyou.dao;

import com.gmc.lvyou.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    public List<RouteImg> findByRid(int rid);
}
