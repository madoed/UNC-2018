package com.netcreker.meetup.controller.user;

import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entity.user.UserCredentials;
import com.netcreker.meetup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @PostMapping("/users/auth")
    public ResponseEntity<?> authenticate(@RequestBody UserCredentials credentials) {
        User user = userService.loadByUsername(credentials);
        if (user == null || !password.equals(user.getPassword())) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> load(@PathVariable long id) {
        User user = userService.load(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/users/")
    public ResponseEntity<List<User>> loadAllUsers() {
        List<User> users = userService.loadAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("/users/")
    public ResponseEntity<?> create(@RequestBody User user) {
        if (userService.exists(user.getUsername())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody User user) {
        User currentUser = userService.load(id);
        if (currentUser==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser.copy(user);
        userService.save(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        User user = userService.load(id);
        if (user==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
