package com.VictorianApp.controller;

import com.VictorianApp.model.SendMethod;
import com.VictorianApp.service.SendMethodService;
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
public class SendMethodController {

    @Autowired
    private SendMethodService sendMethodService;

    @GetMapping(path = "/sendMethods")
    public List<SendMethod> getAll() {
        return sendMethodService.getAll();
    }

    @GetMapping(path = "/sendMethod/{id_wysylki}")
    public Optional<SendMethod> getSendMethod(@PathVariable Integer id_wysylki) {
        return sendMethodService.get(id_wysylki);
    }

    @PostMapping(path = "/sendMethod", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SendMethod> save(@Valid @RequestBody SendMethod sendMethod) throws URISyntaxException {
        sendMethodService.save(sendMethod);
        return ResponseEntity.created(new URI("/api/sendMethod" + sendMethod.getId_wysylki() )).body(sendMethod);
    }

    @PutMapping(path = "/sendMethod")
    ResponseEntity<SendMethod> update(@RequestBody SendMethod sendMethod) {
        sendMethodService.update(sendMethod);
        return ResponseEntity.ok().body(sendMethod);
    }

    @DeleteMapping(path = "/sendMethod/{id_wysylki}")
    ResponseEntity<SendMethod> delete(@PathVariable Integer id_wysylki) {
        sendMethodService.delete(id_wysylki);
        return ResponseEntity.ok().build();
    }

}
