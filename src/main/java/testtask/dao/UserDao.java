package main.java.testtask.dao;


import main.java.testtask.entity.User;

public interface UserDao {
    Integer addUser(User user);

    User getUserByUsername(String username);
}
