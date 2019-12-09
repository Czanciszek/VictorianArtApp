package com.VictorianApp.service;

import com.VictorianApp.dao.ProcedureDao;
import com.VictorianApp.model.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedureService {

    @Autowired
    @Qualifier("psqlProcedure")
    private ProcedureDao procedureDao;

    public List<Procedure> getAll() {
        return this.procedureDao.getAll();
    }

    public Optional<Procedure> get(int id) {
        return this.procedureDao.get(id);
    }

    public List<String> getProcedureNicks() { return this.procedureDao.getProcedureNicks(); }

    public Optional<Integer> getLatestProcedureId() { return this.procedureDao.getLatestProcedureId(); }

    public void save( Procedure procedure) {
        this.procedureDao.save(procedure);
    }

    public void update(Procedure procedure) { this.procedureDao.update(procedure);}

    public void delete(int id) { this.procedureDao.delete(id);}
}