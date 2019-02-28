package com.netcreker.meetup.controller.user;

import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> load(@PathVariable long id) {
        User user = userService.load(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> loadAll() {
        List<User> users = userService.loadAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> getOrCreate(@RequestBody UserDetails userDetails) {
        User user = userService.loadByUsername(userDetails.getUsername());
        System.out.println(user);
        if (user == null) {
            user = new User();
            user.setFirstName(userDetails.getGivenName());
            user.setLastName(userDetails.getFamilyName());
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            userService.save(user);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody User user) {
        User currentUser = userService.load(id);
        if (currentUser==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        currentUser = user.copy();
        userService.save(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        User user = userService.load(id);
        if (user==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
