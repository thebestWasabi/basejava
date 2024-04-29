package ru.maxim_khamzin.webapp.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(final String uuid) {
        super(uuid);
    }
}
