package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseManagerCrudTest {

    @Autowired
    private DatabaseManager dbm = null;

    private List<Map<Long, String>> params1 = new ArrayList<>();
    private List<Map<Long, String>>params2 = new ArrayList<>();
    private List<Map<Long, Long>> refs1 = new ArrayList<>();
    private List<Map<Long, Long>> refs2 = new ArrayList<>();


    @Before
    public void setup() {
        log.debug("DatabaseManager CRUD test startup");

        HashMap<Long, String> map1 = new HashMap<>();
        map1.put(1001L, "first name 1");
        map1.put(1002L, "last name 1");
        map1.put(1003L, "login 1");
        map1.put(1004L, "email 1");
        map1.put(1005L, "password 1");
        map1.put(1017L, "last visit 1");
        params1.add(map1);

        HashMap<Long, String> map2 = new HashMap<>();
        map2.put(1001L, "first name 2");
        map2.put(1002L, "last name 2");
        map2.put(1003L, "login 2");
        map2.put(1004L, "email 2");
        map2.put(1005L, "password 2");
        map2.put(1017L, "last visit 2");
        params1.add(map2);
    }

    @After
    public void cleanup() {
        params1 = null; params2 = null;
        refs1 = null; refs2 = null;
        log.debug("DatabaseManager CRUD test termination");
    }

    @Ignore("Requires manual checking at runtime")
    @Test
    public void crudTest() {
        long id1 = create();
        log.debug("Returned object_id 1: " + id1);
        long id2 = create();
        log.debug("Returned object_id 2: " + id2);

        HashMap<Long, Long> refMap1 = new HashMap<>();
        refMap1.put(1006L, id2);
        refs1.add(refMap1);
        HashMap<Long, Long> refMap2 = new HashMap<>();
        refMap2.put(1006L, id1);
        refs2.add(refMap2);

        update(id1, params1, refs1);
        update(id2, params2, refs2);
        log.debug("Updated test objects");

        delete(id1);
        delete(id2);
        log.debug("Removed test objects from database");
    }

    public long create() {
        long objectType = 1; // user object_type_id
        String name = "test user";
        return dbm.create(objectType, name);
    }

    public void update(long id, List<Map<Long, String>> params, List<Map<Long, Long>> refs) {
        dbm.update(id, params, refs);
    }

    public void delete(long id) {
        dbm.delete(id);
    }

}

