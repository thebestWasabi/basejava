package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage {

    private final Resume[] storage = new Resume[10000];
    private int count;

    public void save(final Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Cannot save null resume");
        }

        final var index = indexOf(resume.getUuid());

        if (index != -1) {
            System.out.println("ERROR Такое резюме уже существует: " + resume.getUuid());
        }
        else if (count == storage.length) {
            System.out.println("ERROR Хранилище для резюме переполнено");
        }
        else {
            storage[count++] = resume;
        }
    }


    public Resume get(final String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot get null uuid");
        }

        final int index = indexOf(uuid);
        return index != -1 ? storage[index] : null;
    }


    public void update(final Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Cannot update null resume");
        }

        final var index = indexOf(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        }
    }


    public void delete(final String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot delete null uuid");
        }

        final int index = indexOf(uuid);

        if (index == -1) {
            System.out.println("ERROR Такого резюме нет: " + uuid);
        }
        else {
            deleteResume(index);
        }
    }


    private int indexOf(final String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


    private void deleteResume(final int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        System.arraycopy(storage, index + 1, storage, index, count - index - 1);
        storage[--count] = null;
    }


    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }


    public void clear() {
        count = 0;
        Arrays.fill(storage, 0, count, null);
    }


    public int size() {
        return count;
    }
}
