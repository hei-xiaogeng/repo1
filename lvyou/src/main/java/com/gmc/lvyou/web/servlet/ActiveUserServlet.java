package com.gmc.lvyou.web.servlet;

import com.gmc.lvyou.service.Userservlet;
import com.gmc.lvyou.service.impl.Userservletimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        //获取激活码
        String code = request.getParameter("code");
        if (code !=null){
            //2.激活
            Userservlet server=new Userservletimpl();

            //查激活码有没有
            boolean flag= server.active(code);

            String msg=null;
            if (flag){
                msg="激活成功请<a href='login.html'>登录<a>";
            }else {
                msg ="激活失败，请联系管理员";
            }

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
