package com.netcreker.meetup.entitymanager;

public interface EntityManager {
    <T> T load(long id, Class<T> clazz);
    void delete(long id);
    void save(Object instance, Class<?> clazz);
}
