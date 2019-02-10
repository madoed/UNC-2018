package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.scheduler.Event;
import com.netcreker.meetup.entity.scheduler.Schedule;
import com.netcreker.meetup.entity.user.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoadTest {

    @Autowired
    private EntityManager em = null;

    @Test
    public void testLazyLoad() {
        User lazyUser =  em.lazyLoad(User.class, -1);
        assertEquals("alice", lazyUser.getUsername());
        assertEquals(null, lazyUser.getFriends());

        User user =  em.load(User.class, -1);
        assertEquals("alice", user.getUsername());
        assertEquals(2, user.getFriends().size());
    }

    @Test
    public void testLoad1() {
        Schedule schedule =  em.load(Schedule.class, -4);
        assertEquals("schedule of Alice", schedule.getName());
        assertEquals("My schedule. Yay!!!", schedule.getDescription());
        assertEquals(0, schedule.getPrivacySetting());
        assertEquals(-1, schedule.getUser().getId());
    }

    @Test
    public void testLoad2() {
        Event event =  em.load(Event.class, -5);
        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.FEBRUARY, 2, 0, 0, 0);

        assertEquals("birthday party", event.getName());
        assertEquals("b-day of Ma", event.getDescription());
        assertEquals(cal.getTime().toString(), event.getStartDate().toString());
        assertEquals(100, event.getPriority());
        assertEquals("party", event.getEventType());
        assertEquals(0, event.getRecursion());
        assertTrue(event.isReminder());
        assertEquals(-4, event.getSchedule().getId());
    }

    @Test
    public void testLoad3() {
        Event event =  em.load(Event.class, -6);
        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.JANUARY, 15, 11, 0, 0);

        assertEquals("painting class", event.getName());
        assertEquals("my painting class", event.getDescription());
        assertEquals(cal.getTime().toString(), event.getEndDate().toString());
        assertEquals(80, event.getPriority());
        assertEquals("class", event.getEventType());
        assertEquals(604800000, event.getRecursion());
        assertFalse(event.isReminder());
        assertEquals(-4, event.getSchedule().getId());
    }

}
