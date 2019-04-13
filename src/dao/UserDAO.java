package dao;

import pojo.User;

public interface UserDAO {
    public Integer addUser(User user);
    public Boolean deleteUser(String wxid);
}
