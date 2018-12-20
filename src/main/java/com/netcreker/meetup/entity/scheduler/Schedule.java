package com.netcreker.meetup.entity.scheduler;

import com.netcreker.meetup.annotation.*;
import com.netcreker.meetup.entity.user.User;
import lombok.Data;

@Data
@ObjectType(-5485260815210642431L)
public class Schedule {
    @Id
    private long id;

    @Parameter(-5485260814833155068L)
    private String name;

    @Parameter(-5485260814833155067L)
    private String description;

    @Parameter(-5485260814833155066L)
    private byte privacySetting;

    @Reference(-5485260814833155065L)
    private User user;
}
