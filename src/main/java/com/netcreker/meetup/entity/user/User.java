package com.netcreker.meetup.entity.user;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    @Reference(1006)
    private List<User> friends = new ArrayList<>();

    @Parameter(1036)
    private String avatarUrl;

    @Parameter(1037)
    private String aboutMe;

    @Parameter(1088)
    private boolean isOnline;

    @Parameter(1089)
    private Date lastVisit;

    @Override
    public String toString() {
        return "Id: " + id + ", name: " + name;
    }
}
