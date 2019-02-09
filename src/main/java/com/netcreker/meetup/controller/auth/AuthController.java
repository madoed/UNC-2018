package com.netcreker.meetup.controller.auth;

import com.netcreker.meetup.entity.user.Role;
import com.netcreker.meetup.entity.user.RoleName;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.security.JwtTokenProvider;
import com.netcreker.meetup.service.user.RoleService;
import com.netcreker.meetup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        if(userService.usernameExists(registrationRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        if(userService.emailExists(registrationRequest.getEmail())) {
            return new ResponseEntity<>("Email Address already in use", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registrationRequest.getFirstName() + " " + registrationRequest.getLastName());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.addRole(roleService.loadByName(RoleName.ROLE_USER));

        userService.save(user);

        return new ResponseEntity<User>(user.copy(), HttpStatus.CREATED);
    }
}
