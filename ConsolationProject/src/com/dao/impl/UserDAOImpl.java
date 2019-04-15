package com.dao.impl;

import com.dao.UserDAO;
import dao.util.DBUtil;
import com.pojo.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public Integer addUser(User user) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into users values('" + user.getWxid() + "', '" + user.getWxName() + "')";
        Statement st = null;
        try {
            st = conn.createStatement();
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, st, conn);
        }
        return 0;
    }

    @Override
    public Boolean deleteUser(String wxid) {
        Connection conn = DBUtil.getConnection();
        String sql = "delete from users where wxid ='" + wxid + "'";

        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(null, st, conn);
        }
        return false;
    }

    @Override
    public Boolean isExited(String wxid) {
        Connection conn = DBUtil.getConnection();
        String seek = "select COUNT(*) from users where wxid ='" + wxid + "'";

        PreparedStatement pstat = null;
        ResultSet rs;
        try {
            pstat = conn.prepareStatement(seek);
            rs = pstat.executeQuery();// 发送查询
            if (rs.next()) {
                if(rs.getInt(1) == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(null, pstat, conn);
        }
        return false;
    }
}
