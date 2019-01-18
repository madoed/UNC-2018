package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.ObjectQuery;
import com.netcreker.meetup.entity.Entity;

import java.util.List;

public interface EntityManager {

    <T extends Entity> void delete(long id);

    <T extends Entity> List<T> filter(Class<T> clazz, ObjectQuery query);

    <T extends Entity> T load(Class<T> clazz, long id);

    <T extends Entity> void save(T entity);

}
