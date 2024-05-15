package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getSearchKey(final String uuid) {
        for (final var entry : storage.entrySet()) {
            if (entry.getValue().getUuid().equals(uuid)) {
                return entry.getKey();
            }
        }
        return null;
    }


    @Override
    protected boolean isExist(final Object index) {
        return index != null;
    }


    @Override
    protected void doSave(final Resume resume, final Object index) {
        storage.put(resume.getUuid(), resume);
    }


    @Override
    protected Resume doGet(final Object index) {
        return storage.get((String) index);
    }


    @Override
    protected void doUpdate(final Resume resume, final Object index) {
        storage.replace((String) index, resume);
    }


    @Override
    protected void doDelete(final Object index) {
        storage.remove((String) index);
    }


    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }


    @Override
    public void clear() {
        storage.clear();
    }


    @Override
    public int size() {
        return storage.keySet().size();
    }
}
