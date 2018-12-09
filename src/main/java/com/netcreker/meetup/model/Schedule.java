package com.netcreker.meetup.model;

import com.netcreker.meetup.annotations.Id;
import com.netcreker.meetup.annotations.Parameter;
import com.netcreker.meetup.annotations.ObjectType;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor @AllArgsConstructor
@ObjectType("schedule")
public class Schedule {
    @Getter @Setter @Id @Parameter(name = "id")
    private long id;

    @Getter @Setter @Parameter(name = "name")
    private String name;

    @Getter @Setter @Parameter(name = "description")
    private String description;

    @Getter @Setter @Parameter(name = "privacy_setting")
    private byte privacySetting;

    @Getter @Setter @Parameter(name = "user_id")
    private int userId;
}
