package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getSearchKey(final String uuid);

    protected abstract boolean isExist(final SK searchKey);

    protected abstract void doSave(final Resume resume, final SK searchKey);

    protected abstract Resume doGet(final SK searchKey);

    protected abstract void doUpdate(final Resume resume, final SK searchKey);

    protected abstract void doDelete(final SK searchKey);

    protected abstract List<Resume> doCopyGetAll();


    @Override
    public void save(final Resume resume) {
        final var searchKey = getNotExistedSearchIndex(resume.getUuid());
        doSave(resume, searchKey);
    }


    @Override
    public Resume get(final String uuid) {
        final var searchKey = getExistedSearchIndex(uuid);
        return doGet(searchKey);
    }


    @Override
    public List<Resume> getAllSorted() {
        final var list = doCopyGetAll();
        Collections.sort(list);
        return list;
    }


    @Override
    public void update(final Resume resume) {
        final var searchKey = getExistedSearchIndex(resume.getUuid());
        doUpdate(resume, searchKey);
    }


    @Override
    public void delete(final String uuid) {
        final var searchKey = getExistedSearchIndex(uuid);
        doDelete(searchKey);
    }


    private SK getExistedSearchIndex(final String uuid) {
        final var searchKey = getSearchKey(uuid);

        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }


    private SK getNotExistedSearchIndex(final String uuid) {
        final var searchKey = getSearchKey(uuid);

        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
