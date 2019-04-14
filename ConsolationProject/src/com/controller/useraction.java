package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class useraction extends HttpServlet {

    private static final long serialVersionUID = 7011671126252616849L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        Integer op = Integer.parseInt(request.getParameter("op"));

        //获取微信小程序get的参数值并打印
        String username = request.getParameter("nickname");
        String password = request.getParameter("password");
        System.out.println("username=" + username + " ,password=" + password);

        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write("进入后台了");
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
