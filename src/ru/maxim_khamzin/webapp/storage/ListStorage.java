package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

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
    protected boolean isExist(final Integer index) {
        return index >= 0;
    }

    @Override
    protected void doSave(final Resume resume, final Integer index) {
        storage.add(resume);
    }

    @Override
    protected Resume doGet(final Integer index) {
        return storage.get(index);
    }

    @Override
    public List<Resume> doCopyGetAll() {
        return new ArrayList<>(storage);
    }

    @Override
    protected void doUpdate(final Resume resume, final Integer index) {
        storage.set(index, resume);  // Обновляем элемент по индексу
    }

    @Override
    protected void doDelete(final Integer index) {
        storage.remove(index.intValue());
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
