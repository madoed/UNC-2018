package com.netcreker.meetup.entity.user;

public class UserCredentials {
    private String username;
    private String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validate(User user) {
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
