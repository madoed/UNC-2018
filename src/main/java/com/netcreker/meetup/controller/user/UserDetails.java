package com.netcreker.meetup.controller.user;

import lombok.Data;

@Data
public class UserDetails {
    private String id;
    private String givenName;
    private String familyName;
    private String username;
    private String email;
}
