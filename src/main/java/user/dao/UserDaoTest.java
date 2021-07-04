package user.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.domain.User;

import java.sql.SQLException;

public final class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);
        User user = new User("seongkyu", "김성규", "developer");

        userDao.addUser(user);
        System.out.println(user.id() + " 등록 성공");

        User user2 = userDao.getUser(user.id());
        System.out.println(user2.name());
        System.out.println(user2.password());
        System.out.println(user2.id() + " 조회 성공");
    }
}
