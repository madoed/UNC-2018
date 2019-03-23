package com.netcreker.meetup.entity.user;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
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

    @Reference(1006)
    private List<User> friends = new ArrayList<>();

    @Parameter(1036)
    private String avatarUrl;

    @Parameter(1037)
    private String aboutMe;

    public User copy() {
        User user = new User();
        user.id = id;
        user.name = name;
        user.firstName = firstName;
        user.lastName = lastName;
        user.username = username;
        user.email = email;
        user.aboutMe = aboutMe;
        user.friends.addAll(friends);
        return user;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", name: " + name;
    }
}
