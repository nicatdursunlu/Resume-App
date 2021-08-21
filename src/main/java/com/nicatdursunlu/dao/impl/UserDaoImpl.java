package com.nicatdursunlu.dao.impl;

import com.nicatdursunlu.bean.Country;
import com.nicatdursunlu.bean.Skill;
import com.nicatdursunlu.bean.User;
import com.nicatdursunlu.bean.UserSkill;
import com.nicatdursunlu.dao.AbstractDao;
import com.nicatdursunlu.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {

    private User getUser(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        int nationalityId = resultSet.getInt("nationality_id");
        int birthplaceId = resultSet.getInt("birthplace_id");
        Date birthDate = resultSet.getDate("birthdate");
        String birthplaceStr = resultSet.getString("birthplace");
        String nationalityStr = resultSet.getString("nationality");

        Country country = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, email, phone, birthDate, country, birthplace);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = connection()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT " +
                    "u.*," +
                    "n.nationality," +
                    "b.name AS birthplace " +
                    "FROM " +
                    "USER u " +
                    "LEFT JOIN country n ON u.nationality_id = n.id " +
                    "LEFT JOIN country b ON u.birthplace_id = b.id ");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                User user = getUser(resultSet);
                result.add(user);
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
            statement.execute("SELECT " +
                    "u.*," +
                    "n.nationality," +
                    "b.name AS birthplace " +
                    "FROM " +
                    "USER u " +
                    "LEFT JOIN country n ON u.nationality_id = n.id " +
                    "LEFT JOIN country b ON u.birthplace_id = b.id where u.id= " + userId);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result = getUser(resultSet);
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

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillsByUserId(int userId) {
        List<UserSkill> result = new ArrayList<>();

        try (Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT " +
                            "u.*," +
                            "us.skill_id," +
                            "s.`name` AS skill_name," +
                            "us.power " +
                            "FROM " +
                            "user_skill us " +
                            "LEFT JOIN USER u ON us.user_id = u.id " +
                            "LEFT JOIN skill s ON us.skill_id = s.id " +
                            "WHERE " +
                            "us.user_id = ?"
            );
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                UserSkill skill = getUserSkill(resultSet);
                result.add(skill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
