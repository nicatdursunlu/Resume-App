package com.nicatdursunlu.main;

import com.nicatdursunlu.dao.EmploymentHistoryDao;
import com.nicatdursunlu.dao.UserDao;
import com.nicatdursunlu.dao.UserSkillDao;
import com.nicatdursunlu.dao.impl.EmploymentHistoryDaoImpl;
import com.nicatdursunlu.dao.impl.UserDaoImpl;
import com.nicatdursunlu.dao.impl.UserSkillDaoImpl;

public class Context {

    public static UserDao instanceUserDao() {
        return new UserDaoImpl();
    }

    public static UserSkillDao instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }

    public static EmploymentHistoryDao instanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }
}
