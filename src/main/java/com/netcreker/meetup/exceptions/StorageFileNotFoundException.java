package com.netcreker.meetup.exceptions;

public class StorageFileNotFoundException extends Exception {
    public StorageFileNotFoundException(Throwable err) {
        super(err);
    }

    public StorageFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public StorageFileNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
