package com.netcreker.meetup.entitymanager;

public class EntityManagerException extends RuntimeException {

    public EntityManagerException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
