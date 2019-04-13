package com.dao;

public abstract class DAOFactory {
    private static DAOFactory df = new DAOFactory1();

    public static DAOFactory instance(){//获取工厂实例
        return df;
    }

    public abstract UserDAO getUserDAO();
    public abstract SongDAO getSongDAO();
    public abstract MoodRecordDAO getMoodRecordDAO();
}

