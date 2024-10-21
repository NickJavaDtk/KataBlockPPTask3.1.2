package ru.kata.spring.boot_security.demo.spring.dao;



import ru.kata.spring.boot_security.demo.spring.model.User;

import java.util.List;

public interface UserDao {
    User getUser(Long id);
    void addUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> getUserList();
    User getUserByUserLogin(String login);


}
