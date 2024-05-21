package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    protected abstract Integer getSearchKey(final String uuid);

    protected abstract void insertElement(final Resume resume, final int index);

    protected abstract void fillDeletedElement(final int index);


    @Override
    protected void doSave(final Resume resume, final Object index) {
        if (count == STORAGE_LIMIT) {
            throw new StorageException("Хранилище для резюме переполнено", resume.getUuid());
        }

        insertElement(resume, (Integer) index);
        count++;
    }


    @Override
    protected Resume doGet(final Object index) {
        return storage[(Integer) index];
    }


    @Override
    public List<Resume> doCopyGetAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, count));
    }


    @Override
    protected void doUpdate(final Resume resume, final Object index) {
        storage[(Integer) index] = resume;
    }


    @Override
    protected void doDelete(final Object index) {
        fillDeletedElement((Integer) index);
        storage[count] = null;
        count--;
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


    @Override
    protected boolean isExist(final Object index) {
        return (Integer) index >= 0;
    }
}
