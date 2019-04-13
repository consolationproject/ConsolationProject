package dao.impl;

import dao.UserDAO;
import dao.util.DBUtil;
import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
}
