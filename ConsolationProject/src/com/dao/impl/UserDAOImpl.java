package com.dao.impl;

import com.dao.UserDAO;
import dao.util.DBUtil;
import com.pojo.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public Integer addUser(User user) {
        Connection conn = DBUtil.getConnection();
        String sql = "INSERT INTO users VALUES ('" + user.getWxId() + "', '" + user.getNickName() + "')";
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
    public Boolean deleteUser(String wxId) {
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM users WHERE wxid ='" + wxId + "'";

        Statement st = null;
        try {
            st = conn.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, st, conn);
        }
        return false;
    }

    @Override
    public Boolean isExited(String wxId) {
        Connection conn = DBUtil.getConnection();
        String seek = "SELECT COUNT(*) FROM users WHERE wxid ='" + wxId + "'";

        PreparedStatement pstat = null;
        ResultSet rs;
        try {
            pstat = conn.prepareStatement(seek);
            rs = pstat.executeQuery();// 发送查询
            if (rs.next()) {
                if (rs.getInt(1) == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, pstat, conn);
        }
        return false;
    }
}
