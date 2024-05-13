package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;


public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(final String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected void insertElement(final Resume resume, final int index) {
        storage[count] = resume;
    }


    @Override
    protected void fillDeletedElement(final int index) {
        storage[index] = storage[count - 1];
    }
}
