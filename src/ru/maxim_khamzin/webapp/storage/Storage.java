package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.List;


public interface Storage {

    void save(final Resume resume);

    Resume get(final String uuid);

    void update(final Resume resume);

    void delete(final String uuid);

    List<Resume> getAllSorted();

    void clear();

    int size();
}
