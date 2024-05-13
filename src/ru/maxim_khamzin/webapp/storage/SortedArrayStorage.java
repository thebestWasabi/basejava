package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    /**
     * Этот метод использует бинарный поиск для нахождения индекса элемента с заданным UUID в массиве хранилища.
     * Если элемент найден, метод возвращает его индекс.
     * Если элемент не найден, метод возвращает отрицательное число,
     * которое может быть использовано для определения места вставки элемента.
     */
    @Override
    protected Integer getSearchKey(final String uuid) {
        final var searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, count, searchKey);
    }


    /**
     * Этот метод вставляет резюме в хранилище по указанному индексу.
     * Он использует системный метод System.arraycopy,
     * чтобы сдвинуть элементы в массиве для освобождения места под новый элемент,
     * и затем вставляет резюме в массив на нужную позицию.
     */
    @Override
    protected void insertElement(final Resume resume, final int index) {
        // https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, count - insertIndex);
        storage[insertIndex] = resume;
    }


    /**
     * <p> Этот метод используется для удаления элемента из хранилища по указанному индексу </p>
     *
     * <p> 1. numMoved = count - index - 1:
     * Определяется количество элементов, которые необходимо переместить влево после удаления элемента.
     * Если numMoved = 0, это означает, что удаляемый элемент находится в конце хранилища, и ничего не требуется сдвигать </p>
     *
     * <p> 2. Если numMoved > 0, вызывается метод System.arraycopy, чтобы сдвинуть оставшиеся элементы влево на одну позицию,
     * чтобы заполнить пустоту, созданную удалением элемента </p>
     */
    @Override
    protected void fillDeletedElement(final int index) {
        int numMoved = count - index - 1; // если numMoved = 0 -  это последний элемент (тогда ничего не надо сдвигать)
        if (numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }
}
