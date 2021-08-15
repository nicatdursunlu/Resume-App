package com.nicatdursunlu.dao.impl;

import com.nicatdursunlu.bean.User;
import com.nicatdursunlu.dao.AbstractDao;
import com.nicatdursunlu.dao.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            Connection connection = connection();
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
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            return statement.execute("update user set name = 'AZAY' where id = 1");

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try {
            Connection connection = connection();
            Statement statement = connection.createStatement();
            return statement.execute("delete from user where id = 1");

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

}
