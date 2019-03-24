package com.netcreker.meetup.controller.user;

import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
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
        if (user == null) {
            user = new User();
            user.setName(userDetails.getFirstName() + userDetails.getLastName());
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            userService.save(user);
        }
        user.setOnline(true);
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody User user) {
        User currentUser = userService.load(id);
        if (currentUser==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
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

    @GetMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable long id) {
        User user = userService.load(id);
        if (user==null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setOnline(false);
        user.setLastVisit(new Date());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
