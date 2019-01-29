package com.netcreker.meetup.databasemanager;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectQueryTest {
    @Autowired
    private DatabaseManager dbm = null;

    @Test
    public void testUnconditionalQuery() {
        ObjectQuery query = ObjectQuery.newInstance();
        assertEquals("select object_id from Objects o", query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        log.debug("Query returned ids:\n" + ids.toString());
    }

    @Test
    public void testQueryWithObjectTypeId() {
        ObjectQuery query = ObjectQuery.newInstance().objectTypeId(1);
        assertEquals("select object_id from Objects o where object_type_id = 1", query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        assertEquals("The query must return a list of size 3", 3, ids.size());
        log.debug("Query returned ids:\n" + ids.toString());
    }

    @Test
    public void testQueryWithObjectName() {
        ObjectQuery query = ObjectQuery.newInstance().objectName("Alice Smith");
        assertEquals("select object_id from Objects o where object_name like 'Alice Smith'", query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        assertEquals("The query must return a single result", 1, ids.size());
        assertEquals("Expected and actual ids must be the same", -1, (long) ids.get(0));
    }

    @Test
    public void testQueryWithReferences() {
        ObjectQuery query = ObjectQuery.newInstance().reference(1006, -2)
                .reference(1006, -3);
        final String result = "select object_id from Objects o where" +
                " object_id in (select object_id from Refs where object_id = o.object_id" +
                " and attr_id = 1006 and (reference = -2 or reference = -3))";
        assertEquals(result, query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        assertEquals("The query must return a single result", 1, ids.size());
        assertEquals("Expected and actual ids must be the same", -1, (long) ids.get(0));
    }

    @Test
    public void testQueryWithOneValue() {
        ObjectQuery query = ObjectQuery.newInstance().value(1002, "%i%");
        final String result = "select object_id from Objects o where" +
                " object_id in (select object_id from Params where object_id = o.object_id" +
                " and attr_id = 1002 and (value like '%i%'))";
        assertEquals(result, query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        assertEquals("The query must return a list of size 2", 2, ids.size());
        log.debug("Query returned ids:\n" + ids.toString());
    }

    @Test
    public void testQueryWithValues() {
        ObjectQuery query = ObjectQuery.newInstance().value(1002, "%i%")
                .value(1004, "%@example.com");
        final String result = "select object_id from Objects o where" +
                " object_id in (select object_id from Params where object_id = o.object_id" +
                " and attr_id = 1002 and (value like '%i%')) and" +
                " object_id in (select object_id from Params where object_id = o.object_id" +
                " and attr_id = 1004 and (value like '%@example.com'))";
        assertEquals(result, query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        assertEquals("The query must return a single result", 1, ids.size());
        assertEquals("Expected and actual ids must be the same", -1, (long) ids.get(0));
    }

    @Test
    public void testFullQuery() {
        ObjectQuery query = ObjectQuery.newInstance().value(1002, "%i%").objectTypeId(1)
                .objectName("Alice Smith").reference(1006, -2).reference(1006, -3);
        final String result = "select object_id from Objects o where" +
                " object_type_id = 1 and object_name like 'Alice Smith' and" +
                " object_id in (select object_id from Refs where object_id = o.object_id" +
                " and attr_id = 1006 and (reference = -2 or reference = -3)) and" +
                " object_id in (select object_id from Params where object_id = o.object_id" +
                " and attr_id = 1002 and (value like '%i%'))";
        assertEquals(result, query.toString());

        List<Long> ids = dbm.queryForObjectIds(query);
        assertEquals("The query must return a single result", 1, ids.size());
        assertEquals("Expected and actual ids must be the same", -1, (long) ids.get(0));
    }
}
