package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.user.User;
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
public class EntityManagerTest {

    @Autowired
    private EntityManager em = null;

    @Test
    public void testLoad()
            throws Exception {
        User user = em.load(-1, User.class);

        assertEquals(-1, user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("alice", user.getLogin());
        assertEquals("qwerty", user.getPassword());
        assertEquals(2, user.getFriends().size());
        //assertEquals(-1, user.getLastVisit());
    }

}
