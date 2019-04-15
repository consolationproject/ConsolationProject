package com.dao.impl;

import com.dao.MoodRecordDAO;
import com.pojo.MoodRecord;
import dao.util.DBUtil;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoodRecordDAOImpl implements MoodRecordDAO {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Integer insertRecord(MoodRecord moodRecord) {
        Connection conn = DBUtil.getConnection();
        String sql = "insert into moodrecord values(null, '" + moodRecord.getWxid() + "','" + moodRecord.getMrgrade() + "','" + dateFormat.format(new Date()) + "')";
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
    public Boolean todayIsRecorded(String wxId) {
        Connection conn = DBUtil.getConnection();
        String seek = "SELECT COUNT(*) FROM moodrecord WHERE wxid ='" + wxId + "' AND mr_date = '" + dateFormat.format(new Date()) + "'";

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

    @Override
    public Integer queryUserTodayMood(String wxId) {
        Connection conn = DBUtil.getConnection();
        String sql = "select mr_grade from moodrecord where wxid = '" + wxId + "' and mr_date = '" + dateFormat.format(new Date()) + "'";

        Statement st = null;
        ResultSet rs = null;

        Integer mood = -1;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            //处理结果集
            while (rs.next()) {
                //处理rs所指向的记录
                mood = rs.getInt(1);
            }
            return mood;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, st, conn);
        }
        return mood;
    }
}
