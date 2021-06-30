package user.dao;

import user.domain.User;

import java.sql.*;

public final class UserDao {

    public final void addUser(final User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        final Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/spring_study", "root", "root");

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
        Class.forName("com.mysql.jdbc.Driver");
        final Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/spring_study", "root", "root");

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
}
