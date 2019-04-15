package com.controller;

import com.dao.DAOFactory;
import com.dao.UserDAO;
import com.pojo.User;
import com.dao.util.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import net.sf.json.JSONObject;

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

        UserDAO userdao = DAOFactory.instance().getUserDAO();

        switch (op) {
            case 1: //用户存入
                //获取微信小程序get的参数值
                String nickName = request.getParameter("nickname");
                //判断用户是否已在数据库内（用户是否曾经登录过小程序）
                boolean flag = userdao.isExited(nickName);
                if(nickName!=null){
                    //若不存在则添加到数据库
                    if (!flag) {
                        User user = new User();
                        user.setWxId(nickName);
                        user.setNickName(nickName);
                        userdao.addUser(user);
                    } else {
                        System.out.println("该用户已存在");
                    }
                }
                break;
            case 2: //获取openId(未成功)
                //小程序唯一标识   (在微信小程序管理后台获取)
                String wxspAppid = "wx41a50694926bd977";
                //小程序的 app secret (在微信小程序管理后台获取)
                String wxspSecret = "b1082c1f8b360ec7a7ab7768563683c3";
                //授权（必填）
                String grant_type = "authorization_code";

                String code = request.getParameter("code");

                String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
                //发送请求
                String sr = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session" + params).toString();
                //解析相应内容（转换成json对象）
                JSONObject json = JSONObject.fromObject(sr);
                //获取会话密钥（session_key）
                String session_key = json.get("session_key").toString();
                //用户的唯一标识（openid）
                String openid = (String) json.get("openid");

                System.out.println(openid);
                break;
        }

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
