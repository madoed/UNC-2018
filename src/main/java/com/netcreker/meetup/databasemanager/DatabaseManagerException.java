package com.netcreker.meetup.databasemanager;

public class DatabaseManagerException extends Exception {
    public DatabaseManagerException () {
        super();
    }

    public DatabaseManagerException (String message, Throwable cause) {
        super(message);
        initCause(cause);
    }

    public DatabaseManagerException (String message) {
        super(message);
    }

    public DatabaseManagerException (Throwable cause) {
        initCause(cause);
    }
}
