package ru.kata.spring.boot_security.demo.spring.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.spring.model.User;


import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager manager;

    @Override
    public User getUser(Long id) {
        return manager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        manager.persist(user);
        manager.flush();
    }

    @Override
    public void updateUser(Long id, User user) {
        User userTmp = getUser(id);
        userTmp.setName(user.getName());
        userTmp.setSurname(user.getSurname());
        userTmp.setAge(user.getAge());
        manager.merge(userTmp);
        manager.flush();
    }

    @Override
    public void deleteUser(Long id) {
        User userTmp = getUser(id);
        manager.remove(userTmp);
        manager.flush();
    }

    @Override
    public List<User> getUserList() {
        String hql = "FROM User";
        TypedQuery<User> query = manager.createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByUserLogin(String login) {
        return  getUserList().stream().filter(log -> log.getUsername().equals(login)).findFirst().orElse(null);
    }


}
