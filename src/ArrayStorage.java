/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int count;

    void clear() {
        // сам storage можно и не чистить, так как его мы выводить не будем (во всяком случае пока)
        this.count = 0;
    }

    void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("Cannot save null resume");
        }

        if (count == storage.length) {
            Resume[] newStorage = new Resume[storage.length * 2];
            System.arraycopy(this.storage, 0, newStorage, 0, this.count);
            this.storage = newStorage;
        }

        this.storage[this.count++] = r;
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
