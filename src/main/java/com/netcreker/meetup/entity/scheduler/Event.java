package com.netcreker.meetup.entity.scheduler;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.annotation.Reference;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.meeting.Meeting;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@ObjectType(3)
public class Event extends Entity {

    @Parameter(1008)
    private String description;

    @Parameter(1011)
    private Date startDate;

    @Parameter(1012)
    private Date endDate;

    @Parameter(1013)
    private byte priority;

    @Parameter(1014)
    private String eventType;

    @Parameter(1015)
    private long recursion;

    @Parameter(1016)
    private boolean reminder;

    @Reference(1017)
    private Schedule schedule;

    @Reference(1018)
    private Meeting meeting;
}
