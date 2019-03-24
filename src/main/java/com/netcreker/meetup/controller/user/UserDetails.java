package com.netcreker.meetup.controller.user;

import lombok.Data;

@Data
public class UserDetails {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
