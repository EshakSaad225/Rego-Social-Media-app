package com.penta.penta_service_posts.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.penta.penta_service_posts.model.UsersDTO;
import com.penta.penta_service_posts.service.UsersService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersResource {

    private final UsersService usersFTService;

    public UsersResource(final UsersService usersFTService) {
        this.usersFTService = usersFTService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUsersFTs() {
        return ResponseEntity.ok(usersFTService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUsersFT(@PathVariable(name = "id") final UUID id) {
        return ResponseEntity.ok(usersFTService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createUsersFT(@RequestBody @Valid final UsersDTO usersFTDTO) {
        final UUID createdId = usersFTService.create(usersFTDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateUsersFT(@PathVariable(name = "id") final UUID id,
            @RequestBody @Valid final UsersDTO usersFTDTO) {
        usersFTService.update(id, usersFTDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUsersFT(@PathVariable(name = "id") final UUID id) {
        usersFTService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
