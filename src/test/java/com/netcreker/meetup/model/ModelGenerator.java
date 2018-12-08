package com.netcreker.meetup.model;

import java.util.HashMap;
import java.util.Map;

public class ModelGenerator {
    public static Schedule getSchedule1() {
        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setName("schedule 1");
        schedule.setDescription("...");
        schedule.setPrivacySetting((byte)0);
        schedule.setUserId(444);
        return schedule;
    }

    public static Map<String, String> getScheduleParams1() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "1");
        params.put("name", "schedule 1");
        params.put("description", "...");
        params.put("privacy_setting", "0");
        params.put("user_id", "444");
        return params;
    }
}
