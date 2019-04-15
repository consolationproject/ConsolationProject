package com.dao;

import com.pojo.MoodRecord;

public interface MoodRecordDAO {

    public Integer insertRecord(MoodRecord moodRecord); //插入一条心情记录数据

    public Boolean todayIsRecorded(String wxId);    //判断该用户当日是否已经选过心情

    public Integer queryUserTodayMood(String wxId); //查询用户当日存在数据库里的心情
}
