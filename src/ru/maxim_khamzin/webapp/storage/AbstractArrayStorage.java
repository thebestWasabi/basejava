package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    protected abstract int indexOf(final String uuid);

    protected abstract void insertElement(final Resume resume, final int index);

    protected abstract void fillDeletedElement(final int index);


    @Override
    public void save(final Resume resume) {
        final var index = indexOf(resume.getUuid());

        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        else if (count == STORAGE_LIMIT) {
            throw new StorageException("Хранилище для резюме переполнено", resume.getUuid());
        }
        else {
            insertElement(resume, index);
            count++;
        }
    }


    @Override
    public Resume get(final String uuid) {
        final int index = indexOf(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        else {
            return storage[index];
        }
    }


    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }


    @Override
    public void update(final Resume resume) {
        final var index = indexOf(resume.getUuid());

        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        else {
            storage[index] = resume;
        }
    }


    @Override
    public void delete(final String uuid) {
        final int index = indexOf(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        else {
            fillDeletedElement(index);
            storage[count--] = null;
        }
    }


    @Override
    public int size() {
        return count;
    }


    @Override
    public void clear() {
        count = 0;
        Arrays.fill(storage, 0, count, null);
    }
}
