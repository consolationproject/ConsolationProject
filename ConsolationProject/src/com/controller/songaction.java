package com.controller;

import com.dao.DAOFactory;
import com.dao.SongDAO;
import com.pojo.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class songaction extends HttpServlet {

    private static final long serialVersionUID = -2022465734151933345L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        Integer op = Integer.parseInt(request.getParameter("op"));

        SongDAO songDAO = DAOFactory.instance().getSongDAO();

        switch (op) {
            case 1:
                Integer sgrade = Integer.parseInt(request.getParameter("mood"));
                List<Song> songList = songDAO.getSongList(sgrade);

                break;
        }

        Writer out = response.getWriter();
        out.write("进入后台了");
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
