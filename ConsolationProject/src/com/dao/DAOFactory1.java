package com.dao;

import com.dao.impl.MoodRecordDAOImpl;
import com.dao.impl.SongDAOImpl;
import com.dao.impl.UserDAOImpl;

public class DAOFactory1 extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public SongDAO getSongDAO() {
        return new SongDAOImpl();
    }

    @Override
    public MoodRecordDAO getMoodRecordDAO() {
        return new MoodRecordDAOImpl();
    }


}
