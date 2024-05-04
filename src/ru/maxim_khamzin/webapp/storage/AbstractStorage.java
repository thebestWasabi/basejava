package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int indexOf(final String uuid);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(final Resume resume, final int index);

    protected abstract Resume doGet(final int index);

    protected abstract void doDelete(final int index);

    @Override
    public void save(final Resume resume) {
        final var index = getNotExistedSearchIndex(resume.getUuid());
        doSave(resume, index);
    }


    @Override
    public Resume get(final String uuid) {
        final var index = getExistedSearchIndex(uuid);
        return doGet(index);
    }


    @Override
    public void update(final Resume resume) {
        final var index = getExistedSearchIndex(resume.getUuid());
        doUpdate(resume, index);
    }


    @Override
    public void delete(final String uuid) {
        final var index = getExistedSearchIndex(uuid);
        doDelete(index);
    }


    private int getExistedSearchIndex(final String uuid) {
        final var index = indexOf(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        else {
            return index;
        }
    }


    private int getNotExistedSearchIndex(final String uuid) {
        final var index = indexOf(uuid);

        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        else {
            return index;
        }
    }
}
