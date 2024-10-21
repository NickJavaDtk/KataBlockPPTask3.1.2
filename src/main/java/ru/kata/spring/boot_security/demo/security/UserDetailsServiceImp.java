package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.spring.dao.UserDao;
import ru.kata.spring.boot_security.demo.spring.model.User;

public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userTmp = userDao.getUserByUserLogin(username);
        if (userTmp == null) {
            throw new UsernameNotFoundException("Пользователь с логином " + username + " не найден");
        }
        return new UserDetailsImp(userTmp);
    }
}
