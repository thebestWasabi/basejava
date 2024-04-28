package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;


public class ArrayStorage extends AbstractArrayStorage implements Storage {

    @Override
    public void save(final Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Cannot save null resume");
        }

        final var index = indexOf(resume.getUuid());

        if (index != -1) {
            System.out.println("ERROR Такое резюме уже существует: " + resume.getUuid());
        }
        else if (count == STORAGE_LIMIT) {
            System.out.println("ERROR Хранилище для резюме переполнено");
        }
        else {
            storage[count++] = resume;
        }
    }

    // Неправильный вариант реализации метода get
    public Resume getNotUsed(final String uuid) {
        if (indexOf(uuid) == -1) {  // тут мы проходим первый раз по массиву
            System.out.println("ERROR");
            return null;
        }
        return storage[indexOf(uuid)];  // а вот тут мы проходим второй раз по массиву
    }


    @Override
    public void update(final Resume resume) {
        if (resume == null) {
            throw new IllegalArgumentException("Cannot update null resume");
        }

        final var index = indexOf(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
        }
    }


    @Override
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


    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, count);
    }


    @Override
    public void clear() {
        count = 0;
        Arrays.fill(storage, 0, count, null);
    }


    protected int indexOf(final String uuid) {
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
}
