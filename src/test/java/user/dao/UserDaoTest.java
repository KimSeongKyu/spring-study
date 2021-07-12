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
}