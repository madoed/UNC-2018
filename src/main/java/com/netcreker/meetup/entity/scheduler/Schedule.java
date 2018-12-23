package com.netcreker.meetup.entity.scheduler;

import com.netcreker.meetup.annotation.*;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;

@Data
@ObjectType(2)
public class Schedule extends Entity {

    @Parameter(attrId=1001)
    private String name;

    @Parameter(attrId=1002)
    private String description;

    @Parameter(attrId=1003)
    private byte privacySetting;

    @Reference(attrId=1004)
    private User user;
}
