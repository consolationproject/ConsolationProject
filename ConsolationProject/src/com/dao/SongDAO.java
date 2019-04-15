package com.dao;

import com.pojo.Song;

import java.util.List;

public interface SongDAO {

    public List<Song> getSongList(Integer sgrade);    //根据用户的选项所对应的分数获得相应的歌曲列表

}
