package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

import static com.netcreker.meetup.entity.EntityGenerator.getScheduleParams;


@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseManagerTest {

    @Autowired
    DatabaseManager dbm = null;

    @Test
    public void testGetValue() {
        List<Map<String, Object>> result = dbm.getValue(1, -5483786440967977981L);
        assertEquals("schedule 1", result.get(0).get("value"));
        assertEquals("schedule one", result.get(1).get("value"));
    }

    @Test
    public void testGetValues() {
        List<Map<String, Object>> result = dbm.getValues(1);
        log.debug(result.toString());
    }

    @Test
    public void testSetValue() {
        dbm.setValue(1, -5483786440724708348L, "this is a test");
    }
}

