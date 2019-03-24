package com.netcreker.meetup.entitymanager;

import com.netcreker.meetup.databasemanager.query.Query;
import com.netcreker.meetup.entity.Entity;

import java.util.List;

public interface EntityManager {

    <T extends Entity> void delete(long id);

    <T extends Entity> List<T> filter(Class<T> clazz, Query query, boolean lazy);

    <T extends Entity> T lazyLoad(Class<T> clazz, long id);

    <T extends Entity> T load(Class<T> clazz, long id);

    <T extends Entity> void save(T entity);

}
