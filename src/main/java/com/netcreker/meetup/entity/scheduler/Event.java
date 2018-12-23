package com.netcreker.meetup.entity.scheduler;

import com.netcreker.meetup.annotation.ObjectType;
import com.netcreker.meetup.annotation.Parameter;
import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.meeting.Meeting;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
@ObjectType(3)
public class Event extends Entity {

    @Parameter(attrId=1001)
    private String name;

    @Parameter(attrId=1002)
    private String description;

    @Parameter(attrId=1005)
    private Date startDate;

    @Parameter(attrId=1006)
    private Date endDate;

    @Parameter(attrId=1007)
    private byte priority;

    @Parameter(attrId=1008)
    private String eventType;

    @Parameter(attrId=1009)
    private Duration recursion;

    @Parameter(attrId=1010)
    private boolean reminder;

    @Parameter(attrId=1011)
    private Schedule schedule;

    @Parameter(attrId=1012)
    private Meeting meeting;
}
