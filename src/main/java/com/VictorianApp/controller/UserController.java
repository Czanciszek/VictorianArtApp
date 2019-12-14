package com.VictorianApp.controller;

import com.VictorianApp.model.User;
import com.VictorianApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(path = "/user/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRATOR', 'ROLE_SUPERUSER', 'ROLE_PRACOWNIK')")
    public Optional<User> getUser(@PathVariable Integer id) {
        return userService.get(id);
    }

    @GetMapping(path = "/user/name/{login}")
    public Optional<User> getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> save(@Valid @RequestBody User user) throws URISyntaxException {
        userService.save(user);
        return ResponseEntity.created(new URI("/api/user" + user.getId() )).body(user);
    }

    @PutMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(path = "/user/{id}")
    ResponseEntity<User> delete(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
