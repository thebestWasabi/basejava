package ru.maxim_khamzin.webapp.exception;

public class StorageException extends RuntimeException {

    private final String uuid;

    public StorageException(final String message) {
        this(message, null, null);
    }

    public StorageException(final String message, final String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(final String message, final Exception e) {
        this(message, null, e);
    }

    public StorageException(final String message, final String uuid, final Exception e) {
        super(message, e);
        this.uuid = uuid;
    }
}
