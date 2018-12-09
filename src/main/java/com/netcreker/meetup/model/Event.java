package com.netcreker.meetup.model;

import com.netcreker.meetup.annotations.Id;
import com.netcreker.meetup.annotations.ObjectType;
import com.netcreker.meetup.annotations.Parameter;
import lombok.*;

import java.time.Duration;
import java.util.Date;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@ObjectType("event")
public class Event {
    @Getter @Setter @Id @Parameter(name = "id")
    private long id;

    @Getter @Setter @Parameter(name = "name")
    private String name;

    @Getter @Setter @Parameter(name = "description")
    private String description;

    @Getter @Setter @Parameter(name = "start_date")
    private Date startDate;

    @Getter @Setter @Parameter(name = "end_date")
    private Date endDate;

    @Getter @Setter @Parameter(name = "priority")
    private byte priority;

    @Getter @Setter @Parameter(name = "event_type")
    private String eventType;

    @Getter @Setter @Parameter(name = "recursion")
    private Duration recursion;

    @Getter @Setter @Parameter(name = "reminder")
    private boolean reminder;

    @Getter @Setter @Parameter(name = "schedule_id")
    private int scheduleId;

    @Getter @Setter @Parameter(name = "meeting_id")
    private int meetingId;
}
