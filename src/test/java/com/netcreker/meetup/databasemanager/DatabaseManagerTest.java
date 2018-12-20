package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


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

    @Test
    public void testSetValues() throws DatabaseManagerException {
        Map<Long, String> map = new HashMap<>();
        map.put(-5483786440967977981L, "schedule 2");
        map.put(-5483786440724708348L, ".....");
        map.put(-5483786440724708347L, "2");
        dbm.setValues(2, map);
    }

    @Test
    public void testGetReference() {
        long id = dbm.getReference(2, -5483786440724708339L);
        assertEquals(1, id);
    }

    @Test
    public void testGetReferences() {
        List<Long> ids = dbm.getReferences(4, -5483786440724708339L);
        assertEquals(2, ids.size());
    }

    @Test
    public void testDelete() {
        dbm.delete(3);
    }

    @Test
    public void testCreate() throws DatabaseManagerException {
        Map<Long, String> params = new HashMap<>();
        params.put(-5483786440967977981L, "create test");
        params.put(-5483786440724708348L, "create test description");
        params.put(-5483786440724708347L, "1");

        Map<Long, Long> refs = new HashMap<>();

        long id = dbm.create(-5483786441311910911L, params, refs);
    }

    @Test
    public void testUpdate() throws DatabaseManagerException {
        Map<Long, String> params = new HashMap<>();
        params.put(-5483786440967977981L, "update test");
        params.put(-5483786440724708348L, "update test description");
        params.put(-5483786440724708345L, "01-01-2000");

        Map<Long, Long> refs = new HashMap<>();
        refs.put(-5483786440724708339L, 1L);

        dbm.update(-1L, params, refs);
    }
}

