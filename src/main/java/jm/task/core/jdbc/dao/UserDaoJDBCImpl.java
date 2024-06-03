package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection = null;

    static {
        try {
            connection = Util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users" +
                    "(id bigserial PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255), age smallint)");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
//statement
        //DROP TABLE IF EXISTS users
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
            System.out.println("Таблица дропнута");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement("insert into Users (name, lastname, age) values (?,?,?)")) {
            ps.setString(1,name);
            ps.setString(2,lastName);
            ps.setByte(3,age);
            ps.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в таблицу");//добавить поля
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement ps = connection.prepareStatement("delete from Users where id = ?")){
            ps.setLong (1, id);
            ps.executeUpdate();
            System.out.println("Пользователь с id " + id + " удален из таблицы");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        try (Statement st = connection.createStatement()){
            ResultSet resultSet  = st.executeQuery("SELECT * FROM Users");

            ArrayList<User> users = new ArrayList<>();

            while (resultSet.next()){
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
            return users;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void cleanUsersTable() {
        try (Statement st = connection.createStatement()) {
           st.executeUpdate("TRUNCATE TABLE Users");
            System.out.println("Таблица очищена");
       } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
