package com.netcreker.meetup.entitymanager;

public interface EntityManager {

    <T extends Object> T load(long id, Class<T> clazz)
            throws IllegalAccessException, InstantiationException, EntityManagerException;

    void delete(long id);

    <T extends Object> void save(T instance);

}
