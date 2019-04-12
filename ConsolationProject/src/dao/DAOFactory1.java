package dao;

import dao.impl.UserDAOImpl;

public class DAOFactory1 extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
