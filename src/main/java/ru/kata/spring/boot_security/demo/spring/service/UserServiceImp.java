package ru.kata.spring.boot_security.demo.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.spring.dao.UserDao;
import ru.kata.spring.boot_security.demo.spring.model.User;



import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserDao dao;

    @Autowired
    BCryptPasswordEncoder encoder;

    public UserServiceImp() {
    }



    @Override
    public User getUser(String id) {
        if(checkLongValue(id)) {
            return dao.getUser(Long.parseLong(id));
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        String s = "";
        user.setPassword(encoder.encode(user.getPassword()));

        dao.addUser(user);
    }

    @Override
    public void updateUser(String id, User user) {
        if (checkLongValue(id)) {
            user.setPassword(encoder.encode(user.getPassword()));
            dao.updateUser(Long.parseLong(id), user);
        }
    }

    @Override
    public void deleteUser(String id) {
       if (checkLongValue(id)) {
           dao.deleteUser(Long.parseLong(id));
       }
    }

    @Override
    public List<User> getUserList() {
        return dao.getUserList();
    }

    @Override
    public boolean checkUserLoginPasswordExist(String login, String password) {
        UserDetails userDetails = loadUserByUsername(login);
        String passUserDetails = userDetails.getPassword();
        return password.equals(encoder.encode(userDetails.getPassword()));
    }

    private boolean checkLongValue(String longValue) {
        long idTmp = 0L;
        try {
            idTmp = Long.parseLong(longValue);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idTmp != 0;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userTmp = dao.getUserByUserLogin(username);
        if (userTmp == null) {
            throw new UsernameNotFoundException("Пользователь с логином " + username + " не найден");
        }
        return new UserDetailsImp(userTmp);

    }
}
