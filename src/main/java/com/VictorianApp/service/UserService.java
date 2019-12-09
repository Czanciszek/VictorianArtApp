package com.VictorianApp.service;

import com.VictorianApp.dao.UserDao;
import com.VictorianApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    @Qualifier("psqlUser")
    private UserDao userDao;

    public List<User> getAll() {
        return this.userDao.getAll();
    }

    public Optional<User> get(int id) {
        return this.userDao.get(id);
    }

    public Optional<User> getUserByLogin(String login) { return this.userDao.getUserByLogin(login); }

    public void save( User user) {
        this.userDao.save(user);
    }

    public void update(User user) { this.userDao.update(user);}

    public void delete(int id) { this.userDao.delete(id);}
}