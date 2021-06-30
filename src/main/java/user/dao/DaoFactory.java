package user.dao;

public final class DaoFactory {

    public final UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public final ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
