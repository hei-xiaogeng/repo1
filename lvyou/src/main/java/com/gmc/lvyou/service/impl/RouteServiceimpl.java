package com.gmc.lvyou.service.impl;

import com.gmc.lvyou.dao.RouteDao;
import com.gmc.lvyou.dao.RouteImgDao;
import com.gmc.lvyou.dao.SellerDao;
import com.gmc.lvyou.dao.impl.RouteDaoimpl;
import com.gmc.lvyou.dao.impl.RouteImgDaoimpl;
import com.gmc.lvyou.dao.impl.SellerDaoimpl;
import com.gmc.lvyou.domain.PageBean;
import com.gmc.lvyou.domain.Route;
import com.gmc.lvyou.domain.RouteImg;
import com.gmc.lvyou.domain.Seller;
import com.gmc.lvyou.service.RouteService;
import com.gmc.lvyou.web.servlet.RouteServlet;
import org.springframework.beans.factory.SmartInitializingSingleton;

import java.util.List;

public class RouteServiceimpl implements RouteService {

    //查分页
    private RouteDao routeDao = new RouteDaoimpl();
    //查详情图片
    private RouteImgDao routeImgDao=new RouteImgDaoimpl();
    //查商家
    private SellerDao sellerDao=new SellerDaoimpl();



    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {


        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);


        //设置总记录数
        int totalCount = routeDao.findTotalCound(cid,rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(String rid) {
        //根据id去route表中查询route对象

        Route route = routeDao.findOne(Integer.parseInt(rid));

        //根据route的id查询图片集合的信息
        List<RouteImg> list = routeImgDao.findByRid(route.getRid());

        //将集合设置到集合对象
        route.setRouteImgList(list);

        //根据route的sid(商家的id)查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

        return route;
    }



}
