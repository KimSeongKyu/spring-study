package user.dao;

public final class DaoFactory {

    public final UserDao userDao() {
        return new UserDao(new DConnectionMaker());
    }
}
