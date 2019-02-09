package com.netcreker.meetup.service.user;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.user.User;
import com.netcreker.meetup.entitymanager.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private EntityManager em;

    public User load(long id) {
        return em.load(User.class, id);
    }

    public List<User> loadAll() {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(1);
        return em.filter(User.class, query, true);
    }

    public User loadByUsernameOrEmail(String usernameOrEmail) {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(1)
                .value(1003, usernameOrEmail);
        List<User> result = em.filter(User.class, query, false);

        if (result.isEmpty()) {
            query = query.clear()
                    .objectTypeId(1)
                    .value(1004, usernameOrEmail);
            result = em.filter(User.class, query, false);
        }
        return result.isEmpty() ? null : result.get(0);
    }

    public boolean usernameExists(String username) {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(1)
                .value(1003, username);
        return !em.filter(User.class, query, true).isEmpty();
    }

    public boolean emailExists(String email) {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(1)
                .value(1004, email);
        return !em.filter(User.class, query, true).isEmpty();
    }

    public void save(User user) {
        em.save(user);
    }

    public void delete(long id) {
        em.delete(id);
    }
}
