package com.nicatdursunlu.dao.impl;

import com.nicatdursunlu.dao.AbstractDao;
import com.nicatdursunlu.dao.UserSkillDao;
import com.nicatdursunlu.entity.Skill;
import com.nicatdursunlu.entity.User;
import com.nicatdursunlu.entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDao {


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

    private UserSkill getUserSkill(ResultSet resultSet) throws Exception {
        int userId = resultSet.getInt("id");
        int skillId = resultSet.getInt("skill_id");
        String skillName = resultSet.getString("skill_name");
        int power = resultSet.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

}
