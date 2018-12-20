package com.netcreker.meetup.entity.scheduler;

import com.netcreker.meetup.annotation.Id;
import com.netcreker.meetup.entity.meeting.Meeting;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
public class Event {
    @Id
    private long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private byte priority;
    private String eventType;
    private Duration recursion;
    private boolean reminder;
    private Schedule schedule;
    private Meeting meeting;
}
