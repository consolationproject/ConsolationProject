package com.dao.impl;

import com.dao.MoodRecordDAO;
import com.pojo.MoodRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoodRecordDAOImpl implements MoodRecordDAO {

    @Override
    public Integer insertRecord(MoodRecord moodRecord) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Connection conn = dao.util.DBUtil.getConnection();
        String sql = "insert into moodrecord(index, mr_id ,wxid, mr_grade, mr_date) values(null, '" + moodRecord.getMrid() + "', '" + moodRecord.getWxid() + "','" + moodRecord.getMrgrade() + "','" + dateFormat.format(new Date()) + "')";
        Statement st = null;
        try {
            st = conn.createStatement();
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dao.util.DBUtil.close(null, st, conn);
        }
        return 0;
    }
}
