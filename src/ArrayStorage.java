import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int count;

    void clear() {
        count = 0;
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Cannot save null resume");
        }

        storage[count++] = r;
    }

    Resume get(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot get null uuid");
        }

        final int index = indexOf(uuid);
        return index != -1 ? storage[index] : null;
    }

    void delete(String uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("Cannot delete null uuid");
        }

        final int index = indexOf(uuid);

        if (index != -1) {
            removeElement(index);
        }
    }

    private int indexOf(final String uuid) {
        for (int i = 0; i < count; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void removeElement(final int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        if (index == count - 1) {
            storage[--count] = null;
        }
        else {
            System.arraycopy(storage, index + 1, storage, index, count - index - 1);
            count--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    int size() {
        return count;
    }
}
