package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;


public class ArrayStorage extends AbstractArrayStorage implements Storage {

    @Override
    protected int indexOf(final String uuid) {
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


    private void deleteResume(final int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        System.arraycopy(storage, index + 1, storage, index, count - index - 1);
        storage[--count] = null;
    }
}
