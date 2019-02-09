package com.netcreker.meetup.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(1)
public class User extends Entity {
    @Parameter(1001)
    private String firstName;

    @Parameter(1002)
    private String lastName;

    @Parameter(1003)
    private String username;

    @Parameter(1004)
    private String email;

    @Parameter(1005)
    private String password;

    @Reference(1006)
    private List<User> friends = new ArrayList<>();

    @Parameter(1007)
    private Date lastVisit;

    @Parameter(1028)
    private String avatar;

    @Parameter(1029)
    private String aboutMe = "";

    @JsonIgnore
    @Reference(1030)
    private List<Role> roles = new ArrayList<>();

    public User copy() {
        User user = new User();
        user.id = id;
        user.name = name;
        user.firstName = firstName;
        user.lastName = lastName;
        user.username = username;
        user.email = email;
        user.password = password;
        user.avatar = avatar;
        user.aboutMe = aboutMe;
        user.roles.addAll(roles);
        user.friends.addAll(friends);
        return user;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    @Override
    public String toString() {
        return "Id: " + id + ", name: " + name;
    }
}
