package com.netcreker.meetup.entity.scheduler;

import com.netcreker.meetup.annotation.*;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(2)
public class Schedule extends Entity {

    @Parameter(1008)
    private String description;

    @Parameter(1009)
    private byte privacySetting;

    @Reference(1010)
    private User user;
}
