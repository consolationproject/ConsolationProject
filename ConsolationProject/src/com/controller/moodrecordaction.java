package com.controller;

import com.dao.DAOFactory;
import com.dao.MoodRecordDAO;
import com.dao.SongDAO;
import com.google.gson.Gson;
import com.pojo.MoodRecord;
import com.pojo.Song;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class moodrecordaction extends HttpServlet {

    private static final long serialVersionUID = -5157229268621286677L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");

        Integer op = Integer.parseInt(request.getParameter("op"));
        Writer out = response.getWriter();

        MoodRecordDAO moodRecordDAO = DAOFactory.instance().getMoodRecordDAO();
        SongDAO songDAO = DAOFactory.instance().getSongDAO();

        switch (op) {
            case 1:
                //获取当日心情

                String wxid = request.getParameter("nickname");
                String moodgrade = request.getParameter("moodgrade");
                Integer mgrade = -1;
                if (moodgrade == null || wxid == null) {
                    System.out.println("参数不正确");
                    out.write("参数不正确");
                    break;
                }
                if (moodRecordDAO.todayIsRecorded(wxid)) {
                    //当日已经提交过心情，则查询之
                    System.out.println("今天已选过心情了哦！");
//                    out.write("今天已选过心情了哦！");
                    mgrade = moodRecordDAO.queryUserTodayMood(wxid);
                } else {
                    //当日未提交过心情，则先插入之
                    mgrade = Integer.parseInt(moodgrade);
                    MoodRecord moodRecord = new MoodRecord();
                    moodRecord.setWxid(wxid);
                    moodRecord.setMrgrade(mgrade);
                    moodRecordDAO.insertRecord(moodRecord);
                }
                //根据选择的心情搜索出对应的歌曲列表，并传到前端
                List<Song> songList = songDAO.getSongList(mgrade);
                Gson gson = new Gson();
                String json = gson.toJson(songList);
                out.write(json);
                out.flush();
                break;
            case 2:
                //获取当前用户的所有心情

                String wxId = request.getParameter("wxid");
                List<MoodRecord> moodList = moodRecordDAO.getUserMoodList(wxId);
                gson = new Gson();
                String moodJson = gson.toJson(moodList);
                out.write(moodJson);
                out.flush();
                break;
        }
        out.flush();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
