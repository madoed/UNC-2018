package com.netcreker.meetup.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    //@Parameter()
    private String avatarUrl;

    private Role role = Role.USER;

    @Reference(1006)
    @JsonIgnore
    private List<User> friends;

    @Parameter(1007)
    private Date lastVisit;

    public void copy(User user) {
        firstName = user.firstName;
        lastName = user.lastName;
        username = user.username;
        email = user.email;
        password = user.password;
        friends = new ArrayList<>(user.friends);
    }

    /*public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }*/

    @Override
    public String toString() {
        return "Id: " + id + ", name: " + name;
    }
}
