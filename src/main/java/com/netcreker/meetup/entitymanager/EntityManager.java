package com.netcreker.meetup.entitymanager;

public interface EntityManager {
    // TODO : add filter method
    <T> T load(long id, Class<T> clazz);
    void delete(long id);
    <T extends Object> void save(T instance);
}
