package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.scheduler.Event;
import com.netcreker.meetup.entity.scheduler.Schedule;
import com.netcreker.meetup.entity.user.User;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveTest {

    @Autowired
    private EntityManager em = null;

    @Test
    public void testSave() {
        Schedule schedule = new Schedule();
        schedule.setUser(em.load(User.class, -2));
        schedule.setDescription("Bob''s schedule");
        schedule.setPrivacySetting((byte) 1);

        em.save(schedule);
        log.debug("Returned schedule id: " + schedule.getId());

        Event event = new Event();
        event.setSchedule(schedule);
        event.setName("test event");
        event.setStartDate(new Date());
        event.setReminder(true);

        em.save(event);
        log.debug("Returned event id: " + event.getId());

        assertNull(event.getSchedule().getName());
        assertEquals(1, event.getSchedule().getPrivacySetting());

        schedule.setName("test schedule");
        schedule.setPrivacySetting((byte) 2);
        em.save(schedule);

        assertEquals("test schedule", event.getSchedule().getName());
        assertEquals(2, event.getSchedule().getPrivacySetting());

        event.setReminder(false);
        em.save(event);
        assertFalse(event.isReminder());

        em.delete(schedule.getId());
        em.delete(event.getId());
    }

}
