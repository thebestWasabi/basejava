package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(final Resume resume, final Object index) {
        storage.add((Integer) index, resume);
    }


    @Override
    protected Resume doGet(final Object index) {
        return storage.get((Integer) index);
    }


    @Override
    protected void doUpdate(final Resume resume, final Object index) {
        storage.set((Integer) index, resume);  // Обновляем элемент по индексу
    }


    @Override
    protected void doDelete(final Object index) {
        storage.remove(((Integer) index).intValue());
    }


    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }


    @Override
    public void clear() {
        storage.clear();
    }


    @Override
    public int size() {
        return storage.size();
    }


    @Override
    protected Integer getSearchKey(final String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected boolean isExist(final Object index) {
        return (Integer) index >= 0;
    }
}
