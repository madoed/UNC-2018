package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.Entity;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.util.Reflection;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityManagerTest {

    @Autowired
    private EntityManager em = null;

    /*
    private final long objectType = 1; // user object_type_id
    private final String name = "Alice Smith";
    private Entity alice;
    private Entity bob;


    @Before
    public void setup() {
        alice = new User();
        ((User) alice).setFirstName("Alice");
        ((User) alice).setLastName("Smith");
        ((User) alice).setEmail("alice@example.com");
        ((User) alice).setLogin("alice");
        ((User) alice).setPassword("qwerty");
        ((User) alice).setLastVisit(new Date());

        bob = new User();
        ((User) bob).setFirstName("Bob");
        ArrayList<User> friends = new ArrayList<>();
        friends.add((User) bob);
    }
    */

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

}
