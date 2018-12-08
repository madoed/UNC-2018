package com.netcreker.meetup.model;

import com.netcreker.meetup.annotations.Id;
import com.netcreker.meetup.annotations.Parameter;

import java.time.Duration;
import java.util.Date;

public class Event {
    @Id
    @Parameter(name = "id")
    private int id;
    @Parameter(name = "name")
    private String name;
    @Parameter(name = "description")
    private String description;
    @Parameter(name = "start_date")
    private Date startDate;
    @Parameter(name = "end_date")
    private Date endDate;
    @Parameter(name = "priority")
    private byte priority;
    @Parameter(name = "event_type")
    private String eventType;
    @Parameter(name = "recursion")
    private Duration recursion;
    @Parameter(name = "reminder")
    private boolean reminder;
    @Parameter(name = "schedule_id")
    private int scheduleId;
    @Parameter(name = "meeting_id")
    private int meetingId;

    public Event() {}

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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public byte getPriority() {
        return priority;
    }

    public String getEventType() {
        return eventType;
    }

    public Duration getRecursion() {
        return recursion;
    }

    public boolean getReminder() {
        return reminder;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getMeetingId() {
        return meetingId;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setRecursion(Duration recursion) {
        this.recursion = recursion;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + "]";
    }

}
