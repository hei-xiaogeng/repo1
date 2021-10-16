package com.gmc.lvyou.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmc.lvyou.domain.Category;
import com.gmc.lvyou.service.CategoryService;
import com.gmc.lvyou.service.impl.CategoryServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceimpl();
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> cs=service.findAll();
        //System.out.println(cs);
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("applicateion/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);

    }
    }
