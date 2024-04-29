package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int indexOf(final String uuid) {
        final var searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, count, searchKey);
    }


    @Override
    protected void insertElement(final Resume resume, final int index) {
        // https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, count - insertIndex);
        storage[insertIndex] = resume;
    }


    @Override
    protected void fillDeletedElement(final int index) {
        int numMoved = count - index - 1; // если numMoved = 0 -  это последний элемент (тогда ничего не надо сдвигать)
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }
}
