package com.curso.de.tecnologia.controller;

import com.curso.de.tecnologia.dto.UserDTO;
import com.curso.de.tecnologia.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "API para gestão de usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Criar um novo usuário", description = "Cria um novo usuário a partir de um UserDTO")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar usuário por nome de usuário", description = "Retorna um usuário pelo nome de usuário")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<UserDTO> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar usuário por email", description = "Retorna um usuário pelo email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        Optional<UserDTO> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Excluir usuário por ID", description = "Deleta um usuário pelo seu ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verificar se o nome de usuário já existe", description = "Verifica se o nome de usuário já existe no sistema")
    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> usernameExists(@PathVariable String username) {
        boolean exists = userService.usernameExists(username);
        return ResponseEntity.ok(exists);
    }

    @Operation(summary = "Verificar se o email já existe", description = "Verifica se o email já existe no sistema")
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> emailExists(@PathVariable String email) {
        boolean exists = userService.emailExists(email);
        return ResponseEntity.ok(exists);
    }

}
