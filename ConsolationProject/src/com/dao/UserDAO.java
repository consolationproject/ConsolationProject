package com.dao;

import com.pojo.User;

public interface UserDAO {
    public Integer addUser(User user);
    public Boolean deleteUser(String wxid);
    public Boolean isExited(String wxid);
}
