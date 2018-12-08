package com.netcreker.meetup.entitymanager;

public interface EntityManager {
    <T> T load(int id, Class<T> clazz);
    void delete(int id);
    void save(Object instance, Class<?> clazz);
}
