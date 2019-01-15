package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.util.Reflection;
import lombok.extern.log4j.Log4j;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityManagerTest {

    @Autowired
    private EntityManager em = null;

    @Test
    public void testLoad() {
        User user = em.load(User.class, -1);

        assertEquals(-1, user.getId());
        assertEquals("Alice Smith", user.getName());
        assertEquals("Alice", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("alice", user.getLogin());
        assertEquals("qwerty", user.getPassword());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals(2, user.getFriends().size());

        Calendar cal = Calendar.getInstance();
        cal.set(2019, Calendar.JANUARY, 8, 22, 53, 54);
        Date date = cal.getTime();
        assertEquals(Reflection.dateFormat.format(date), Reflection.dateFormat.format(user.getLastVisit()));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void failLoad() {
        em.load(User.class, -100500);
    }

    @Test
    public void testSave() {
        long id = -2;
        User user = new User();
        user.setId(-2);
        user.setName("Bob Wilde");
        user.setFirstName("Bob");
        user.setLastName("Wilde");
        user.setLastVisit(new Date());
        em.save(user);

        User loadedUser = em.load(User.class, id);
        assertEquals("Loaded user must be equal to the created one", user, loadedUser);
    }

    @Ignore("Requires manual checking at runtime")
    @Test
    public void testCreateAndDelete() {
        User user = new User();
        user.setName("test user");
        user.setFirstName("test");
        user.setLastName("user");
        user.setLogin("user");
        user.setPassword("1111");
        user.setEmail("user@example.com");
        user.setLastVisit(new Date());
        ArrayList<User> friends = new ArrayList<>();
        friends.add(em.load(User.class, -2));
        friends.add(em.load(User.class, -3));
        user.setFriends(friends);

        em.save(user);
        em.delete(user.getId());
    }

}
