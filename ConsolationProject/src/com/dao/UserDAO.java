package com.dao;

import com.pojo.User;

public interface UserDAO {
    public Integer addUser(User user);

    public Boolean deleteUser(String wxId);

    public Boolean isExited(String wxId);
}
