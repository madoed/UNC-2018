package com.netcreker.meetup.exceptions;

public class StorageException extends Exception {
    public StorageException(Throwable err) {
        super(err);
    }

    public StorageException(String errorMessage) {
        super(errorMessage);
    }

    public StorageException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
