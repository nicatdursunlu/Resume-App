package com.nicatdursunlu.dao;

import com.nicatdursunlu.entity.EmploymentHistory;

import java.util.List;

public interface EmploymentHistoryDao {

    public List<EmploymentHistory> getAllEmploymentHistories(int userId);
}
