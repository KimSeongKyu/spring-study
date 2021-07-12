package user.dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import user.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {

    @Test
    public void addAndGetUserTest() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        User addedUser = new User("seongkyu", "김성규", "developer");

        userDao.addUser(addedUser);
        assertThat(userDao.getCount()).isEqualTo(1);

        User gotUser = userDao.getUser(addedUser.id());

        assertThat(gotUser.name()).isEqualTo(addedUser.name());
        assertThat(gotUser.password()).isEqualTo(addedUser.password());
    }

    @Test
    public void getCountTest() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = context.getBean("userDao", UserDao.class);

        User addedUser1 = new User("seongkyu1", "김성규", "developer");
        User addedUser2 = new User("seongkyu2", "김성규", "developer");
        User addedUser3 = new User("seongkyu3", "김성규", "developer");

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.addUser(addedUser1);
        assertThat(userDao.getCount()).isEqualTo(1);

        userDao.addUser(addedUser2);
        assertThat(userDao.getCount()).isEqualTo(2);

        userDao.addUser(addedUser3);
        assertThat(userDao.getCount()).isEqualTo(3);
    }
}