package com.nicatdursunlu.dao;

import com.nicatdursunlu.entity.User;
import com.nicatdursunlu.entity.UserSkill;

import java.util.List;

public interface UserSkillDao {

    public List<UserSkill> getAllSkillsByUserId(int userId);
}
