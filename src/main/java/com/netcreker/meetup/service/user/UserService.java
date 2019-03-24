package com.netcreker.meetup.service.user;

import com.netcreker.meetup.databasemanager.query.ObjectQuery;
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

    public User loadByUsername(String username) {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(1)
                .value(1003, username);
        List<User> result = em.filter(User.class, query, false);

        return result.isEmpty() ? null : result.get(0);
    }

    public User loadByKeycloakId(String keycloakId) {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(1)
                .value(1005, keycloakId);
        List<User> result = em.filter(User.class, query, false);

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
