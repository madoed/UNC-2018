package com.netcreker.meetup.model;

import com.netcreker.meetup.annotations.*;

public class Schedule {
    @Id
    @Parameter(name = "id")
    private int id;
    @Parameter(name = "name")
    private String name;
    @Parameter(name = "description")
    private String description;
    @Parameter(name = "privacy_setting")
    private byte privacySetting;
    @Parameter(name = "user_id")
    private int userId;

    public Schedule() {}

    public Schedule(int id, String name, String description,
                    byte privacySetting, int userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacySetting = privacySetting;
        this.userId = userId;
    }

    /**
     * Getters
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte getPrivacySetting() {
        return privacySetting;
    }

    public int getUserId() {
        return userId;
    }

    /**
     * Setters
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrivacySetting(byte privacySetting) {
        this.privacySetting = privacySetting;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Schedule [id=" + id + ", name=" + name + ", userId=" + userId +"]";
    }
}
