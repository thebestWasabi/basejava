package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorage implements Storage {

    protected abstract int indexOf(final String uuid);

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(final Resume resume);

    protected abstract Resume doGet(final int index);

    protected abstract void doDelete(final int index);


    @Override
    public void save(final Resume resume) {
        final var index = getNotExistedSearchIndex(resume.getUuid());
        doSave(resume);
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

    public static void main(String[] args) {
        ListStorage listStorage = new ListStorage();
        listStorage.save(new Resume("001"));
        listStorage.save(new Resume("002"));
        listStorage.save(new Resume("003"));

        System.out.println(Arrays.toString(listStorage.getAll()));

    }
}
