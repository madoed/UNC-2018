package com.netcreker.meetup.databasemanager;

public class DatabaseManagerTests {

    /*
    @Test
    public void testGetValue() {
        List<Map<String, Object>> result = dbm.getValue(-1, 1001);
        assertEquals("Alice", result.get(0).get("value"));
        log.debug("Name param of user with id -1 is: " + result.toString());
    }

    @Test
    public void testGetValues() {
        List<Map<String, Object>> result = dbm.getValues(-1);
        assertEquals(5, result.size());
        log.debug("Friends refs of user with id -1 are:" + result.toString());
    }

    @Test
    public void testSetValue() {
        dbm.setValue(-2, 1001, "Bob");
    }

    @Test
    public void testSetValues() {
        Map<Long, String> map = new HashMap<>();
        map.put(1013L, "bob");
        map.put(1014L, "bob@example.com");
        map.put(1015L, "123456");
        map.put(1017L, "23-12-2018 22:48:00");
        dbm.setValues(-2, map);
    }

    @Test
    public void testGetReference() {
        //long id = dbm.getReference(2, -5483786440724708339L);
        //assertEquals(1, id);
    }

    @Test
    public void testGetReferences() {
        List<Long> ids = dbm.getReferences(-1, 1016);
        assertEquals(2, ids.size());
        log.debug(ids.toString());
    }

    @Test
    public void testCreate() {
        Map<Long, String> params = new HashMap<>();
        params.put(1001L, "create test name");
        params.put(1013L, "create test username");

        Map<Long, Long> ref1 = new HashMap<>();
        ref1.put(1013L, -2L);
        Map<Long, Long> ref2 = new HashMap<>();
        ref2.put(1013L, -1L);

        List<Map<Long, Long>> refs = new ArrayList<>();
        refs.add(ref1);
        refs.add(ref2);

        long id = dbm.create(1, params, refs);
        log.debug("Created new object with id: " + id);
    }

    @Test
    public void testDelete() {
        //dbm.delete(0);
    }

    @Test
    public void testUpdate() {
        Map<Long, String> params = new HashMap<>();
        params.put(-5483786440967977981L, "update test");
        params.put(-5483786440724708348L, "update test description");
        params.put(-5483786440724708345L, "01-01-2000");

        Map<Long, Long> refs = new HashMap<>();
        refs.put(-5483786440724708339L, 1L);

        dbm.update(-1L, params, refs);
    }
    */
}
