package com.netcreker.meetup.service.user;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.user.Role;
import com.netcreker.meetup.entity.user.RoleName;
import com.netcreker.meetup.entitymanager.EntityManager;
import com.netcreker.meetup.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private EntityManager em;

    public Role load(long id) {
        return em.load(Role.class, id);
    }

    public Role loadByName(RoleName roleName) {
        ObjectQuery query = ObjectQuery.newInstance()
                .objectTypeId(8).objectName(roleName.name());
        List<Role> result = em.filter(Role.class, query, true);
        if (result.isEmpty())
            throw new ResourceNotFoundException("Role", "name", roleName.name());
        return result.get(0);
    }
}
