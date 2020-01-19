package com.ebbi.EbbiCards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) throws EntityCantBeFoundException {
        Optional<User> optionalUser = userService.getUserById(id);
        User user = optionalUser.orElseThrow(() -> new EntityCantBeFoundException(id, User.class));

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/login/login={login}&password={password}")
    public Integer login(@PathVariable(value = "login") String login,
                         @PathVariable(value = "password") String password) {
        if (login.equals("admin") && password.equals("admin")) {
            return 1;
        }
        return -1;
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public ResponseEntity<Long> createUser(@RequestParam(name = "user") User user) {
        long isSaved = userService.saveUser(user);
        return ResponseEntity.ok().body(isSaved);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteUser(@RequestParam(name = "user") User user) {
        boolean isDeleted = userService.deleteUser(user);
        return ResponseEntity.ok().body(isDeleted);
    }
}
