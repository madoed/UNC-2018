package com.netcreker.meetup.exceptions;

public class EntityManagerException extends RuntimeException {

    public EntityManagerException(Throwable err) {
        super(err);
    }

    public EntityManagerException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
