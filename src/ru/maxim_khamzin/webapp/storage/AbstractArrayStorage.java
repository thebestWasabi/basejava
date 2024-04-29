package ru.maxim_khamzin.webapp.storage;

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
        if (resume == null) {
            throw new IllegalArgumentException("Cannot save null resume");
        }

        final var index = indexOf(resume.getUuid());

        if (index > 0) {
            System.out.println("ERROR Такое резюме уже существует: " + resume.getUuid());
        }
        else if (count == STORAGE_LIMIT) {
            System.out.println("ERROR Хранилище для резюме переполнено");
        }
        else {
            insertElement(resume, index);
            count++;
        }
    }


    @Override
    public Resume get(final String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot get null uuid");
        }

        final int index = indexOf(uuid);
        return index > 0 ? storage[index] : null;
    }


    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }


    @Override
    public void update(final Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Cannot update null resume");
        }

        final var index = indexOf(resume.getUuid());

        if (index > 0) {
            storage[index] = resume;
        }
    }


    @Override
    public void delete(final String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot delete null uuid");
        }

        final int index = indexOf(uuid);

        if (index < 0) {
            System.out.println("ERROR Такого резюме нет: " + uuid);
        }
        else {
            fillDeletedElement(index);
            storage[count--] = null;
        }
    }


    public int size() {
        return count;
    }


    @Override
    public void clear() {
        count = 0;
        Arrays.fill(storage, 0, count, null);
    }

}
