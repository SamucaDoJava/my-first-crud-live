package com.curso.de.tecnologia.service;

import com.curso.de.tecnologia.builder.UserMapper;
import com.curso.de.tecnologia.dto.UserDTO;

import com.curso.de.tecnologia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO saveUser(UserDTO userDTO) {
        var userEntity = userMapper.toEntity(userDTO);
        var savedUser = userRepository.save(userEntity);
        return userMapper.toDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO);
    }

    public Optional<UserDTO> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDTO);
    }

    public Optional<UserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO);
    }

    public List<UserDTO> getAllUsers() {
        return userMapper.toListDTO(userRepository.findAll());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}
