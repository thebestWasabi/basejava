package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void save(final Resume resume) {
        final var index = indexOf(resume.getUuid());

        if (index != -1) {
            throw new ExistStorageException(resume.getUuid());
        }
        else {
            storage.add(resume);
        }
    }


    @Override
    public Resume get(final String uuid) {
        final var index = indexOf(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        else {
            return storage.get(index);
        }
    }


    @Override
    public void update(final Resume resume) {
        final var index = indexOf(resume.getUuid());

        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        else {
            storage.set(index, resume);  // Обновляем элемент по индексу
        }
    }


    @Override
    public void delete(final String uuid) {
        final var index = indexOf(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        else {
            storage.remove(index);
        }
    }


    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);

//        final var result = new Resume[storage.size()];
//        for (int i = 0; i < storage.size(); i++) {
//            result[i] = storage.get(i);
//        }
//        return result;
    }


    @Override
    public void clear() {
        storage.clear();
    }


    @Override
    public int size() {
        return storage.size();
    }


    private int indexOf(final String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
