package com.nicatdursunlu.dao.impl;

import com.nicatdursunlu.bean.User;
import com.nicatdursunlu.dao.AbstractDao;
import com.nicatdursunlu.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from user");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                result.add(new User(id, name, surname, email, phone));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getById(int userId) {
        User result = new User();
        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            statement.execute("select * from user where id=" + userId);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                result = new User(id, name, surname, email, phone);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = connection()) {
            PreparedStatement statement =
                    connection.prepareStatement("update user set name=?, surname=?, email=?, phone=?  where id=?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setInt(5, user.getId());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        try (Connection connection = connection()) {
            PreparedStatement statement =
                    connection.prepareStatement("insert into user(name, surname, email, phone) values(?,?,?,?)");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            return statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            return statement.execute("delete from user where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
