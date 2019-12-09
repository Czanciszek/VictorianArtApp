package com.VictorianApp.service;

import com.VictorianApp.dao.SendMethodDao;
import com.VictorianApp.model.SendMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SendMethodService {

    @Autowired
    @Qualifier("psqlSendMethod")
    private SendMethodDao sendMethodDao;

    public List<SendMethod> getAll() {
        return this.sendMethodDao.getAll();
    }

    public Optional<SendMethod> get(int id) {
        return this.sendMethodDao.get(id);
    }

    public void save( SendMethod sendMethod) {
        this.sendMethodDao.save(sendMethod);
    }

    public void update(SendMethod sendMethod) { this.sendMethodDao.update(sendMethod);}

    public void delete(int id) { this.sendMethodDao.delete(id);}
}