package com.gmc.lvyou.web.servlet;

import com.gmc.lvyou.domain.PageBean;
import com.gmc.lvyou.domain.Route;
import com.gmc.lvyou.service.FavoriteService;
import com.gmc.lvyou.service.RouteService;
import com.gmc.lvyou.service.impl.FavoriteServiceimpl;
import com.gmc.lvyou.service.impl.RouteServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService=new RouteServiceimpl();
    private FavoriteService favoriteService =new FavoriteServiceimpl();

    /**
     * 分页查询 pageQuery
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");


        //接受路线名称
        String rname = request.getParameter("rname");

        //解决老tomcat7乱码
        //rname = new String(rname.getBytes("iso-8859-1"), "utf-8");


        int cid = 0;//类别id
        //2.处理参数

        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }


        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }


        System.out.println(currentPage);
        System.out.println(pageSize);
        System.out.println(cid);
        System.out.println(rname);



        //3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);
        System.out.println(pb);

        //4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);


    }

    /**
     * 根据id 查询一个旅游线路的详情信息
     * @param request
     * @param response
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1接受id
        String rid = request.getParameter("rid");

        //2.调用service查询route对象
        Route route = routeService.findOne(rid);

        //3.转为json写回客户端
        writeValue(route,response);


    }
}




