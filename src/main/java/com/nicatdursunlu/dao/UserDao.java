package com.nicatdursunlu.dao;

import com.nicatdursunlu.bean.User;

import java.util.List;

public interface UserDao {

    public List<User> getAll();

    public User getById(int id);

    public boolean updateUser(User user);

    public boolean addUser(User user);

    public boolean removeUser(int id);
}
