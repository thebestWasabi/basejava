package ru.maxim_khamzin.webapp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;


public abstract class AbstractStorageTest {

    private static final String UUID_1 = "001";
    private static final String UUID_2 = "002";
    private static final String UUID_3 = "003";
    private static final String UUID_4 = "004";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected final Storage storage;

    protected AbstractStorageTest(final Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }


    //========================SAVE=============================
    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveIfResumeExist() throws Exception {
        storage.save(RESUME_2);
    }


    //========================GET=============================
    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAll() {
        final var all = storage.getAll();
        assertEquals(3, all.length);
        assertEquals(RESUME_1, all[0]);
        assertEquals(RESUME_2, all[1]);
        assertEquals(RESUME_3, all[2]);
    }


    //========================UPDATE=============================
    @Test
    public void updateIfResumeExist() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfResumeNotExist() throws Exception {
        Resume newResume = new Resume("dummy");
        storage.update(newResume);
    }


    //========================DELETE=============================
    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteIfUuidNotExist() {
        storage.delete("006");
    }


    //========================OTHER=============================
    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    private void assertSize(final int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(final Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}