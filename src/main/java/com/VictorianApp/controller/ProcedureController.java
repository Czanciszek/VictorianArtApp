package com.VictorianApp.controller;

import com.VictorianApp.model.Procedure;
import com.VictorianApp.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_SUPERUSER', 'ROLE_DRUKARZ', 'ROLE_PRACOWNIK')")
public class ProcedureController {

    @Autowired
    private ProcedureService procedureService;

    @GetMapping(path = "/procedures")
    public List<Procedure> getAll() {
        return procedureService.getAll();
    }

    @GetMapping(path = "/procedure/{id_zamowienia}")
    public Optional<Procedure> getProcedure(@PathVariable Integer id_zamowienia) {
        return procedureService.get(id_zamowienia);
    }

    @GetMapping(path = "/procedure/nicks")
    public List<String> getProcedureNicks() {
        return procedureService.getProcedureNicks();
    }

    @GetMapping(path ="/procedure/latest")
    public Optional<Integer> getLatestProcedureId() {
        return procedureService.getLatestProcedureId();
    }

    @PostMapping(path = "/procedure", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Procedure> save(@Valid @RequestBody Procedure procedure) throws URISyntaxException {
        procedureService.save(procedure);
        return ResponseEntity.created(new URI("/api/procedure" + procedure.getId_zamowienia() )).body(procedure);
    }

    @PutMapping(path = "/procedure")
    ResponseEntity<Procedure> update(@RequestBody Procedure procedure) {
        procedureService.update(procedure);
        return ResponseEntity.ok().body(procedure);
    }

    @DeleteMapping(path = "/procedure/{id_zamowienia}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERUSER')")
    ResponseEntity<Procedure> delete(@PathVariable Integer id_zamowienia) {
        procedureService.delete(id_zamowienia);
        return ResponseEntity.ok().build();
    }

}
