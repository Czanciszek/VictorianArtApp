package com.VictorianApp.controller;

import com.VictorianApp.model.Role;
import com.VictorianApp.service.RoleService;
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
@PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_SUPERUSER')")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(path = "/roles")
    public List<Role> getAll() {
        return roleService.getAll();
    }

    @GetMapping(path = "/role/{id}")
    public Optional<Role> getRole(@PathVariable Integer id) {
        return roleService.get(id);
    }

    @GetMapping(path = "/role/roles")
    public List<String> getRoles() {
        return roleService.getRoles();
    }

    @PostMapping(path = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Role> save(@Valid @RequestBody Role role) throws URISyntaxException {
        roleService.save(role);
        return ResponseEntity.created(new URI("/api/role" + role.getId() )).body(role);
    }

    @PutMapping(path = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Role> update(@RequestBody Role role) {
        roleService.update(role);
        return ResponseEntity.ok().body(role);
    }

    @DeleteMapping(path = "/role/{id}")
    ResponseEntity<Role> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
