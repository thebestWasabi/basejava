package ru.maxim_khamzin.webapp.storage;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        ObjectFileStorageTest.class,
        ObjectPathStorageTest.class,
})
public class AllStorageTest {
}
