package com.netcreker.meetup.entitymanager;

public interface EntityManager {

    Object load() throws IllegalAccessException, InstantiationException;

}
