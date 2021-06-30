package user.dao;

import user.domain.User;

import java.sql.*;

public abstract class UserDao {

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

    public final void addUser(final User user) throws ClassNotFoundException, SQLException {
        final Connection connection = getConnection();

        final PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        preparedStatement.setString(1, user.id());
        preparedStatement.setString(2, user.name());
        preparedStatement.setString(3, user.password());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }


    public final User getUser(final String id) throws ClassNotFoundException, SQLException {
        final Connection connection = getConnection();

        final PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from users where id = ?");
        preparedStatement.setString(1, id);

        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        final User user = new User(resultSet.getString("id"),
                resultSet.getString("name"), resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
