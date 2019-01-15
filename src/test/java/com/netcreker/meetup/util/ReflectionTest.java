package com.netcreker.meetup.util;

import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.scheduler.Schedule;
import com.netcreker.meetup.entity.user.User;
import lombok.extern.log4j.Log4j;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.*;

@Log4j
public class ReflectionTest {

    private User user1;
    private User user2;
    private Schedule schedule;

    @Before
    public void setMockData() {
        user1 = new User();
        user1.setId(1);
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setLogin("alice");
        user1.setPassword("qwerty");
        user1.setLastVisit(new Date());

        user2 = new User();
        user2.setId(2);
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        user2.setLogin("bob");
        user2.setPassword("123456");
        user2.setLastVisit(new Date());

        ArrayList<User> user1Friends = new ArrayList<>();
        user1Friends.add(user2);
        user1.setFriends(user1Friends);

        ArrayList<User> user2Friends = new ArrayList<>();
        user2Friends.add(user1);
        user2.setFriends(user2Friends);

        schedule = new Schedule();
        schedule.setId(10000);
        schedule.setName("My schedule");
        schedule.setDescription("Alice's schedule");
        schedule.setPrivacySetting((byte)2);
        schedule.setUser(user1);
    }

    @After
    public void cleanupMockData() {
        user1 = null;
        user2 = null;
        schedule = null;
    }

    @Test
    public void testGetParamFields() {
        Map<Long, Field> result = Reflection.getParamFields(User.class);
        assertEquals("User class has 6 parameter fields", 6, result.size());
        result.forEach((k, v) -> log.debug("attr_id: " + k + ", field name: " + v.getName()));

        result = Reflection.getParamFields(Schedule.class);
        assertEquals("Schedule class has 2 parameter fields", 2, result.size());
        result.forEach((k, v) -> log.debug("attr_id: " + k + ", field name: " + v.getName()));
    }

    @Test
    public void testGetRefFields() {
        Map<Long, Field> result = Reflection.getRefFields(User.class);
        assertEquals("User class has 1 reference fields", 1, result.size());
        result.forEach((k, v) -> log.debug("attr_id: " + k + ", field name: " + v.getName()));

        result = Reflection.getRefFields(Schedule.class);
        assertEquals("Schedule class has 1 reference fields", 1, result.size());
        result.forEach((k, v) -> log.debug("attr_id: " + k + ", field name: " + v.getName()));
    }


    @Test
    public void testGetReference() throws IllegalAccessException, NoSuchFieldException {
        Field field = User.class.getDeclaredField("friends");
        List<Long> result = Reflection.getReference(field, user1);
        assertEquals("user1 has only 1 friend", 1, result.size());
        assertEquals("user2 is user1's friend", (Long) user2.getId(), result.get(0));

        field = Schedule.class.getDeclaredField("user");
        result = Reflection.getReference(field, schedule);
        assertEquals("Schedule can have a reference only to 1 user", 1, result.size());
        assertEquals("THis schedule should reference user1", (Long) user1.getId(), result.get(0));
    }


    @Test
    public void testGetValue() throws IllegalAccessException, NoSuchFieldException {
        Field field = User.class.getDeclaredField("lastVisit");
        List<String> result = Reflection.getValue(field, user1);
        assertEquals("Field values must be equal",
                Reflection.dateFormat.format(user1.getLastVisit()), result.get(0));

        field = Schedule.class.getDeclaredField("privacySetting");
        result = Reflection.getValue(field, schedule);
        assertEquals("Field values must be equal", "2", result.get(0));
    }

    @Test
    public void testSetReferenceField() throws IllegalAccessException, NoSuchFieldException, ParseException {
        log.debug("schedule reference user " + schedule.getUser().getName());
        Reflection.setField(Schedule.class.getDeclaredField("user"), schedule, user2);
        assertEquals("Now schedule must reference user " + user2.getName(), user2, schedule.getUser());
    }

    @Test
    public void testSetReferenceListField() throws IllegalAccessException, NoSuchFieldException, ParseException {
        int friendsCount = user1.getFriends().size();
        log.debug("Initially, user1 has " + friendsCount + " friends");
        Reflection.setField(User.class.getDeclaredField("friends"), user1, new User());
        log.debug("Added 1 friend to user1");
        assertEquals("user1 must have " + (friendsCount + 1) + " friends",
                friendsCount + 1,
                user1.getFriends().size());
    }

    @Test
    public void testSetParameterField() throws IllegalAccessException, NoSuchFieldException, ParseException {
        Calendar cal = Calendar.getInstance();
        cal.clear(Calendar.MILLISECOND);
        cal.set(2018, Calendar.JANUARY, 1, 0, 0, 0);
        Date newDate = cal.getTime();
        Reflection.setField(User.class.getDeclaredField("lastVisit"), user2,
                Reflection.dateFormat.format(newDate));
        assertEquals("Field value must equal set value", user2.getLastVisit(), newDate);
    }

    @Test
    public void testGetObjectType() throws ReflectiveOperationException {
        assertEquals("User.class object_type_id must be equal 1",
                1, Reflection.getObjectType(User.class));
    }

    @Test(expected = ReflectiveOperationException.class)
    public void testFailGetObjectType() throws ReflectiveOperationException {
        Reflection.getObjectType(Entity.class);
    }

}
