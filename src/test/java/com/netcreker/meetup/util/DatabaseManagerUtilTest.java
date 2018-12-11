package com.netcreker.meetup.util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import static com.netcreker.meetup.util.DatabaseManagerUtil.*;


public class DatabaseManagerUtilTest {
    @Test
    public void testParamSelect() {
        String query1 =
                "select * from meetupdb.Params natural join meetupdb.Attributes" +
                " where object_id = 1" +
                " and attr_name in ('id')";
        assertEquals(paramSelect(1, "id"), query1);

        String query2 = "select * from meetupdb.Params natural join meetupdb.Attributes" +
                " where object_id = 1" +
                " and attr_name in ('id','name')";
        List<String> list = new ArrayList<>(2);
        list.add("id");
        list.add("name");
        assertEquals(paramSelect(1, list), query2);
    }

    @Test
    public void testParamInsert() {
        String query =
                "insert into meetupdb.Params (object_id, attr_id, value)" +
                " select 54, attr_id, 'aaa'" +
                " from meetupdb.Attributes where attr_name like 'attr'";
        assertEquals(paramInsert(54, "attr", "aaa"), query);
    }
}
