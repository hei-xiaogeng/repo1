package com.gmc.lvyou.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class userServlet extends BaseServlet {
    /**
     * 调用单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // System.out.println("findone被执行了");
        Object user = request.getSession().getAttribute("user");

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");//必须要设置
        mapper.writeValue(response.getOutputStream(),user);
    }





}
