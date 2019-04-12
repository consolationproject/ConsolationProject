package dao;

import dao.impl.MoodRecordDAOImpl;
import dao.impl.SongDAOImpl;
import dao.impl.UserDAOImpl;

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
