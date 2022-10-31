package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getInstance().getConnection();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable()  {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE if not exists `users` (\n" +
                    "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) NOT NULL,\n" +
                    "  `age` int NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable()  {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age)  {
        try (PreparedStatement preparedStatement = connection.
                prepareStatement("INSERT INTO users(name,lastName,age) VALUES (?,?,?);")) {
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id)  {
        PreparedStatement preStatement;
        try {
            preStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preStatement.setLong(1, id);
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers()  {
        Statement statement;
        List <User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void cleanUsersTable()  {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE  users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
