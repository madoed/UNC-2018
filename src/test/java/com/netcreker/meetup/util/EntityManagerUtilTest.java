package com.netcreker.meetup.util;

import com.netcreker.meetup.model.Schedule;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static com.netcreker.meetup.model.ModelGenerator.getScheduleParams;

public class EntityManagerUtilTest {
    @Test
    public void testGetParameters() {
        List<String> params = EntityManagerUtil.getParameterAttrNames(Schedule.class);
        assertEquals(5, params.size());
        System.out.println(params);
    }

    @Test
    public void testSetParameters() {
        Schedule schedule = new Schedule();
        Map<String, String> params = getScheduleParams();
        EntityManagerUtil.setParameters(schedule, schedule.getClass(), params);

        assertEquals(2, schedule.getId());
        assertEquals("schedule 2", schedule.getName());
        assertEquals("...", schedule.getDescription());
        assertEquals(0, schedule.getPrivacySetting());
        assertEquals(444, schedule.getUserId());
    }
}
