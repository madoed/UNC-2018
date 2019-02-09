package com.netcreker.meetup.entity.user;

public class UserCredentials {
    private final String username;
    private final String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validate(User user) {
        return username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
