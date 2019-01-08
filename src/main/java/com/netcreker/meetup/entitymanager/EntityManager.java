package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.Entity;

public interface EntityManager {

    <T extends Entity> void delete(T entity);

    <T extends Entity> T load(Class<T> clazz, long id);

    <T extends Entity> void save(T entity);

}
