package ru.kata.spring.boot_security.demo.spring.model;



import java.util.List;


public interface UserService {
    User getUser(String id);

    void addUser(User user);

    void updateUser(String id, User user);

    void deleteUser(String id);

    List<User> getUserList();
}
