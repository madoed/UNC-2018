package com.netcreker.meetup.entity;

import com.netcreker.meetup.entity.scheduler.*;
import com.netcreker.meetup.entity.user.User;

import java.util.HashMap;
import java.util.Map;

public class EntityGenerator {
    public static Schedule getSchedule() {
        User user = new User();
        user.setId(444);

        Schedule schedule = new Schedule();
        schedule.setId(1);
        schedule.setName("schedule 1");
        schedule.setDescription("...");
        schedule.setPrivacySetting((byte)0);
        schedule.setUser(user);

        return schedule;
    }

    public static Map<String, String> getScheduleParams() {
        Map<String, String> params = new HashMap<>();
        params.put("id", "2");
        params.put("name", "schedule 2");
        params.put("description", "...");
        params.put("privacy_setting", "0");
        params.put("user", "444");
        return params;
    }
}
