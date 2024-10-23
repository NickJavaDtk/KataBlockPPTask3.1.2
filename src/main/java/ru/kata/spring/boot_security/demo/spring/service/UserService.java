package ru.kata.spring.boot_security.demo.spring.service;



import ru.kata.spring.boot_security.demo.spring.model.User;

import java.util.List;


public interface UserService {
    User getUser(String id);

    void addUser(User user);

    void updateUser(String id, User user);

    void deleteUser(String id);

    List<User> getUserList();

    boolean checkUserLoginPasswordExist(String login, String password);
}
