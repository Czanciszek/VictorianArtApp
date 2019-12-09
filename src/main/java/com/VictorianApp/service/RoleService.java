package com.VictorianApp.service;

import com.VictorianApp.dao.RoleDao;
import com.VictorianApp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    @Qualifier("psqlRole")
    private RoleDao roleDao;

    public List<Role> getAll() {
        return this.roleDao.getAll();
    }

    public Optional<Role> get(int id) {
        return this.roleDao.get(id);
    }

    public List<String> getRoles() {
        return this.roleDao.getRoles();
    }

    public void save( Role role) {
        this.roleDao.save(role);
    }

    public void update(Role role) { this.roleDao.update(role);}

    public void delete(int id) { this.roleDao.delete(id);}
}