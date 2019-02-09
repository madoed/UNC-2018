package com.netcreker.meetup.security;

import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.exceptions.ResourceNotFoundException;
import com.netcreker.meetup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPrincipalService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userService.loadByUsernameOrEmail(usernameOrEmail);
        if (user == null)
            throw new ResourceNotFoundException("User", "username/email", usernameOrEmail);
        return UserPrincipal.fromUser(user);
    }

    @Transactional
    public UserDetails loadUserById(long id) {
        User user = userService.load(id);
        if (user == null)
            throw new ResourceNotFoundException("User", "id", id);
        return UserPrincipal.fromUser(user);
    }
}
