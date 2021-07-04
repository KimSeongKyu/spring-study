package user.dao;

import user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserDao {

    private DataSource dataSource;

    public final void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public final void addUser(final User user) throws SQLException {
        final Connection connection = dataSource.getConnection();

        final PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        preparedStatement.setString(1, user.id());
        preparedStatement.setString(2, user.name());
        preparedStatement.setString(3, user.password());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public final User getUser(final String id) throws SQLException {
        final Connection connection = dataSource.getConnection();

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
