package ru.maxim_khamzin.webapp.exception;

public class StorageException extends RuntimeException {

    private final String uuid;

    public StorageException(final String message, final String uuid) {
        super(message);
        this.uuid = uuid;
    }
}
