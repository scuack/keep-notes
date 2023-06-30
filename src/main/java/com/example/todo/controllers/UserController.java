package com.example.todo.controllers;

import com.example.todo.entities.User;
import com.example.todo.respositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
public class UserController {
    @Autowired

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<User> findById(@PathVariable Long idUser){
    Optional<User> userOptional = userRepository.findById(idUser);
    if (userOptional.isPresent()){
        return ResponseEntity.ok(userOptional.get());
    }else{
        return ResponseEntity.notFound().build();
    }
    }

    @PostMapping("/users/create")
    public ResponseEntity<User> create(@RequestBody User user, @RequestHeader HttpHeaders header){
        if (user.getIdUser() != null){
            log.warn("Traying to create user with id");
            return ResponseEntity.badRequest().build();
        }else {
            return ResponseEntity.ok(userRepository.save(user));
        }
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@RequestBody User user){
        if (user.getIdUser() == null){
            log.warn("Traying to update user a non existent user");
            return ResponseEntity.badRequest().build();
        }else if (!userRepository.existsById(user.getIdUser())){
            log.warn("Traying to update a non exist user");
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(userRepository.save(user));
        }
    }

    @DeleteMapping("/users/{idUser}")
    public ResponseEntity<User> delete(@PathVariable Long idUser){
        if (!userRepository.existsById(idUser)){
            log.warn("Traying to delete a non exist user");
            return ResponseEntity.notFound().build();
        }else {
            userRepository.deleteById(idUser);
            return ResponseEntity.noContent().build();
        }

    }
}
