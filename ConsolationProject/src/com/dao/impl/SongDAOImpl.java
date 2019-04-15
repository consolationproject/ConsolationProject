package com.dao.impl;

import com.dao.SongDAO;
import com.pojo.Song;
import dao.util.DBUtil ;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl implements SongDAO {

    @Override
    public List<Song> getSongList(Integer sgrade) {
        List<Song> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        String sql = "SELECT * FROM songs WHERE s_grade = " + sgrade;

        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                Song song = new Song();
                song.setSid(rs.getInt(1));
                song.setSname(rs.getString(2));
                song.setScover(rs.getString(3));
                song.setSlink(rs.getString(4));
                song.setSgrade(rs.getInt(5));
                list.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(rs,st, conn);
        }
        return list;
    }
}
