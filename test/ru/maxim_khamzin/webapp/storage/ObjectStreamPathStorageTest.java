package ru.maxim_khamzin.webapp.storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIRECTORY.getAbsolutePath()));
    }
}
