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

        User addedUser1 = new User("seongkyu1", "김성규", "developer");
        User addedUser2 = new User("seongkyu2", "김성규", "developer");

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.addUser(addedUser1);
        userDao.addUser(addedUser2);
        assertThat(userDao.getCount()).isEqualTo(2);

        User gotUser1 = userDao.getUser(addedUser1.id());
        assertThat(gotUser1.name()).isEqualTo(addedUser1.name());
        assertThat(gotUser1.password()).isEqualTo(addedUser1.password());

        User gotUser2 = userDao.getUser(addedUser2.id());
        assertThat(gotUser2.name()).isEqualTo(addedUser2.name());
        assertThat(gotUser2.password()).isEqualTo(addedUser2.password());
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