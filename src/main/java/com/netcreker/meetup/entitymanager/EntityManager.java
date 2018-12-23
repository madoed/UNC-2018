package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.entity.Entity;

public interface EntityManager {

    <T extends Entity> void delete(T entity)
            throws EntityManagerException;

    <T extends Entity> T load(long id, Class<T> clazz)
            throws Exception;

    <T extends Entity> void save(T entity)
            throws EntityManagerException;

}
