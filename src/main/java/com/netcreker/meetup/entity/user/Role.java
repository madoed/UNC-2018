package com.netcreker.meetup.entity.user;

public enum Role {
    ADMIN ("admin"),
    USER ("user");

    final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role fromName(String name) {
        for (Role r : Role.values()) {
            if (r.name.equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }
}
