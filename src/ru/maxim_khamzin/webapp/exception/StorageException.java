package ru.maxim_khamzin.webapp.exception;

public class StorageException extends RuntimeException {

    private final String uuid;

    public StorageException(final String uuid) {
        this.uuid = uuid;
    }
}
