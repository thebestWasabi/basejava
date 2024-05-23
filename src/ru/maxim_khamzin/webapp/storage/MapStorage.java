package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {

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
    protected boolean isExist(final String uuid) {
        return uuid != null && storage.containsKey(uuid);
    }

    @Override
    protected void doSave(final Resume resume, final String uuid) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(final String uuid) {
        return storage.get(uuid);
    }

    @Override
    public List<Resume> doCopyGetAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    protected void doUpdate(final Resume resume, final String uuid) {
        storage.replace(uuid, resume);
    }

    @Override
    protected void doDelete(final String uuid) {
        storage.remove(uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
