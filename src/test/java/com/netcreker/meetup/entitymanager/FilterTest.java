package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.user.User;
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
public class FilterTest {
    @Autowired
    private EntityManager em = null;

    @Test
    public void testFilterWithOneResult() {
        ObjectQuery query = ObjectQuery.newInstance().reference(1006, -2)
                .reference(1006, -3);
        List<User> results = em.filter(User.class, query, false);
        assertEquals("The query must return a single result", 1, results.size());
        assertEquals(-1, results.get(0).getId());
    }

    @Test
    public void testFilterWithManyResults() {
        ObjectQuery query = ObjectQuery.newInstance().objectTypeId(1);
        List<User> results = em.filter(User.class, query, false);
        assertEquals("The query must return a list of size 3", 3, results.size());
        log.debug("Query returned result:\n" + results.toString());
    }
}
