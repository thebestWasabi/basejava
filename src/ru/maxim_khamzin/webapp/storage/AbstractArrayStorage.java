package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count;

    public int size() {
        return count;
    }

    @Override
    public Resume get(final String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot get null uuid");
        }

        final int index = indexOf(uuid);
        return index != -1 ? storage[index] : null;
    }

    protected abstract int indexOf(final String uuid);
}
