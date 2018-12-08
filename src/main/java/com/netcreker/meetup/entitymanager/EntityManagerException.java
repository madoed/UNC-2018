package com.netcreker.meetup.entitymanager;

public class EntityManagerException extends Exception {
    public EntityManagerException () {
        super();
    }

    public EntityManagerException (String message, Throwable cause) {
        super(message);
        initCause(cause);
    }

    public EntityManagerException (String message) {
        super(message);
    }

    public EntityManagerException (Throwable cause) {
        initCause(cause);
    }
}
