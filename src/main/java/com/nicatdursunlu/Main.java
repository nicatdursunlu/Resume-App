package com.nicatdursunlu;

import com.nicatdursunlu.bean.User;
import com.nicatdursunlu.dao.UserDao;
import com.nicatdursunlu.dao.impl.UserDaoImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        UserDao userDao = new UserDaoImpl();

//        List<User> users = userDao.getAll();
//        userDao.removeUser(1);
//
//        List<User> users2 = userDao.getAll();
//        System.out.println(users);
//        System.out.println(users2);
//
//        User user = new User();
//        user.setId(2);
//        user.setName("Azay updated");
//        userDao.updateUser(user);

//        User user = userDao.getById(2);
//        user.setName("Azay");
//        userDao.updateUser(user);

        User user = new User(0, "Sarkhan", "Rasullu", "srkhan.rasullu@gmail.com", "+9945552512");
        userDao.addUser(user);
    }
}
