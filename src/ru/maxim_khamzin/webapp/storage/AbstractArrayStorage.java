package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    protected abstract int indexOf(final String uuid);

    protected abstract void insertElement(final Resume resume, final int index);

    protected abstract void fillDeletedElement(final int index);


    @Override
    protected void doSave(final Resume resume, final int index) {
        if (count == STORAGE_LIMIT) {
            throw new StorageException("Хранилище для резюме переполнено", resume.getUuid());
        }

        insertElement(resume, index);
        count++;
    }


    @Override
    protected Resume doGet(final int index) {
        return storage[index];
    }


    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }


    @Override
    protected void doUpdate(final Resume resume, final int index) {
        storage[index] = resume;
    }


    @Override
    protected void doDelete(final int index) {
        fillDeletedElement(index);
        storage[count--] = null;
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
