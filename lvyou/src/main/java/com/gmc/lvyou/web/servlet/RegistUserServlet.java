package com.gmc.lvyou.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmc.lvyou.domain.ResultInfo;
import com.gmc.lvyou.domain.User;
import com.gmc.lvyou.service.Userservlet;
import com.gmc.lvyou.service.impl.Userservletimpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        if (checkcode_server==null || !checkcode_server.equalsIgnoreCase(check)){
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(String.valueOf(json));
            return;
        }

        //封装对象
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //注册
        Userservlet servlet = new Userservletimpl();
        boolean flag= servlet.regist(user);

        //状态类
        ResultInfo info = new ResultInfo();

        if (flag){
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json写回到客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
