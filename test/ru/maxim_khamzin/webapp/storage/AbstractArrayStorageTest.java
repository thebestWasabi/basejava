package ru.maxim_khamzin.webapp.storage;

import org.junit.Test;
import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(final IStorage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveIfStorageOverFlow() throws Exception {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Name" + i));
            }
        }
        catch (Exception ex) {
            fail("Переполнение массива произошло раньше времени");
        }
        storage.save(new Resume("OverFlowName"));
    }
}
