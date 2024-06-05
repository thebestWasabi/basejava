package ru.maxim_khamzin.webapp.storage;

import org.junit.Before;
import org.junit.Test;
import ru.maxim_khamzin.webapp.exception.ExistStorageException;
import ru.maxim_khamzin.webapp.exception.NotExistStorageException;
import ru.maxim_khamzin.webapp.model.ContactType;
import ru.maxim_khamzin.webapp.model.ListSection;
import ru.maxim_khamzin.webapp.model.Organization;
import ru.maxim_khamzin.webapp.model.OrganizationSection;
import ru.maxim_khamzin.webapp.model.Resume;
import ru.maxim_khamzin.webapp.model.SectionType;
import ru.maxim_khamzin.webapp.model.TextSection;

import java.io.File;
import java.time.Month;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public abstract class AbstractStorageTest {

    protected static final File STORAGE_DIRECTORY = new File("/Users/wasabi/work/IdeaProjects/basejava/storage");

    private static final String UUID_1 = "001";
    private static final String UUID_2 = "002";
    private static final String UUID_3 = "003";
    private static final String UUID_4 = "004";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, "Name1");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        RESUME_4 = new Resume(UUID_4, "Name4");

        RESUME_1.addContact(ContactType.MOBILE, "8-999-111-11-11");
        RESUME_1.addContact(ContactType.TELEGRAM, "@myTelegram");

        RESUME_1.addSection(SectionType.OBJECTIVE, new TextSection("Java-разработчик"));
        RESUME_1.addSection(SectionType.PERSONAL, new TextSection("Personal information"));
        RESUME_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        RESUME_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "Collections", "Algorithms", "SQL"));

        RESUME_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Company1", "https://company.ru/",
                                new Organization.Position(2022, Month.AUGUST, "разработчик", "content")),

                        new Organization("Luxkod", "https://luxkod.ru/",
                                new Organization.Position(2023, Month.AUGUST, "Учитель", "teacher content"))
                ));

        RESUME_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Skillfactory", "https://skillfactory.ru",
                                new Organization.Position(2019, Month.SEPTEMBER, 2020, Month.DECEMBER, "Java-разработка", "описание")),

                        new Organization("МУ им. С.Ю. Витте", "https://luxkod.ru",
                                new Organization.Position(2019, Month.SEPTEMBER, 2022, Month.MARCH, "Java-разработка", "описание"))
                ));
    }

    protected final IStorage storage;

    protected AbstractStorageTest(final IStorage storage) {
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
    public void getAllSorted() {
        final var all = storage.getAllSorted();
        assertEquals(3, all.size());
        assertEquals(all, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
    }


    //========================UPDATE=============================
    @Test
    public void updateIfResumeExist() throws Exception {
        Resume newResume = new Resume(UUID_1, "NewName");
        storage.update(newResume);
//        assertSame(newResume, storage.get(UUID_1));
        assertTrue(newResume.equals(storage.get(UUID_1)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateIfResumeNotExist() throws Exception {
        Resume newResume = new Resume("dummy", "name");
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