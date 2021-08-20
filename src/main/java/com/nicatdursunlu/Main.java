package com.nicatdursunlu;

import com.nicatdursunlu.dao.UserDao;
import com.nicatdursunlu.dao.impl.UserDaoImpl;

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

//        System.out.println(userDao.getAll());
        System.out.println(userDao.getAllSkillsByUserId(5));
    }
}
