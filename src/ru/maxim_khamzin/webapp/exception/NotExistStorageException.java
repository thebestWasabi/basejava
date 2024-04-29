package ru.maxim_khamzin.webapp.exception;

public class NotExistStorageException extends StorageException {

    public NotExistStorageException(final String uuid) {
        super(uuid);
    }
}
