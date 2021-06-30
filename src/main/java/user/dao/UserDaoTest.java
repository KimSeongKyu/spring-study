package user.dao;

import user.domain.User;

import java.sql.SQLException;

public final class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDao userDao = new UserDao();

        User user = new User("seongkyu", "김성규", "developer");

        userDao.addUser(user);

        System.out.println(user.id() + " 등록 성공");

        User user2 = userDao.getUser(user.id());
        System.out.println(user2.name());
        System.out.println(user2.password());

        System.out.println(user2.id() + " 조회 성공");
    }
}
