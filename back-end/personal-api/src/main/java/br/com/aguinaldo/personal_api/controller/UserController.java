package br.com.aguinaldo.personal_api.controller;

import br.com.aguinaldo.personal_api.model.User;
import br.com.aguinaldo.personal_api.service.UserService;
import br.com.aguinaldo.personal_api.service.dto.UserDTO;
import br.com.aguinaldo.personal_api.service.translator.UserTranslator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserTranslator userTranslator;

    @Autowired
    public UserController(UserService userService, UserTranslator userTranslator) {
        this.userService = userService;
        this.userTranslator = userTranslator;
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto) {
        try {
            User userUpdate = userTranslator.toEntity(dto);
            userService.save(userUpdate);
            UserDTO updatedUser = userTranslator.toDTO(userUpdate);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, iae.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAllUsers();
        List<UserDTO> dtos = users
                .stream()
                .map(userTranslator::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        try {
            User user = userService.findUserById(id);
            UserDTO dto = userTranslator.toDTO(user);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, iae.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id) {
        try {
            User userUpdate = userTranslator.toEntity(userDTO);
            User updatedUser = userService.updateUser(id, userUpdate);
            UserDTO updatedDTO = userTranslator.toDTO(updatedUser);
            return ResponseEntity.ok(updatedDTO);
        } catch (IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, iae.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(1) Long id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException iae) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, iae.getMessage());
        }
    }



}
