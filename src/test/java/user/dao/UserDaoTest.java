package user.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
class UserDaoTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserDao userDao;

    private User addedUser1;
    private User addedUser2;
    private User addedUser3;

    @BeforeEach
    public void setUp() {
        DataSource dataSource = new SingleConnectionDataSource(
                "jdbc:mysql://localhost/spring_study_test", "root", "root", true);
        userDao.setDataSource(dataSource);
        addedUser1 = new User("seongkyu1", "김성규", "developer");
        addedUser2 = new User("seongkyu2", "김성규", "developer");
        addedUser3 = new User("seongkyu3", "김성규", "developer");
    }

    @Test
    public void addAndGetUserTest() throws SQLException {
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
    public void getUserFailTest() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        assertThatExceptionOfType(EmptyResultDataAccessException.class).isThrownBy(() -> {
            userDao.getUser("unknownId");
        });
    }

    @Test
    public void getCountTest() throws SQLException {
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