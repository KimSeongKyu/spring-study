package user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public final class DaoFactory {

    @Bean
    public final UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public final ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
