package com.nicatdursunlu.main;

import com.nicatdursunlu.dao.EmploymentHistoryDao;
import com.nicatdursunlu.dao.UserSkillDao;

public class Main {

    public static void main(String[] args) throws Exception {

        EmploymentHistoryDao employmentHistoryDao = Context.instanceEmploymentHistoryDao();

        System.out.println(employmentHistoryDao.getAllEmploymentHistories(5));
    }
}
