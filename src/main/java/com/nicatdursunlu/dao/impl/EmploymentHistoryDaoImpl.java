package com.nicatdursunlu.dao.impl;

import com.nicatdursunlu.dao.AbstractDao;
import com.nicatdursunlu.dao.EmploymentHistoryDao;
import com.nicatdursunlu.entity.EmploymentHistory;
import com.nicatdursunlu.entity.Skill;
import com.nicatdursunlu.entity.User;
import com.nicatdursunlu.entity.UserSkill;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmploymentHistoryDaoImpl extends AbstractDao implements EmploymentHistoryDao {

    @Override
    public List<EmploymentHistory> getAllEmploymentHistories(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();

        try (Connection connection = connection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "select * from employment_history where user_id = ?"
            );
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                EmploymentHistory employmentHistory = getUserEmploymentHistory(resultSet);
                result.add(employmentHistory);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private EmploymentHistory getUserEmploymentHistory(ResultSet resultSet) throws Exception {

        String header = resultSet.getString("header");
        String jobDescription = resultSet.getString("job_description");
        Date beginDate = resultSet.getDate("begin_date");
        Date endDate = resultSet.getDate("end_date");
        int userId = resultSet.getInt("user_id");

        EmploymentHistory employmentHistory = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
        return employmentHistory;
    }
}
