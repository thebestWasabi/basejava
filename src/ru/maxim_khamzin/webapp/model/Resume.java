package ru.maxim_khamzin.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Resume implements Comparable<Resume>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(final String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(final String uuid, final String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");

        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid)
               && Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

//    @Override
//    public String toString() {
//        return "{uuid='%s', fullName='%s'}".formatted(uuid, fullName);
//    }


    @Override
    public String toString() {
        return "Resume{uuid='%s', fullName='%s', \ncontacts=%s, \nsections=%s}"
                .formatted(uuid, fullName, contacts, sections);
    }

    @Override
    public int compareTo(final Resume o) {
        var result = String.CASE_INSENSITIVE_ORDER.compare(fullName, o.fullName);
        return result == 0 ? uuid.compareTo(o.uuid) : result;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContact(final ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(final SectionType type) {
        return sections.get(type);
    }

    public void addContact(final ContactType type, final String value) {
        contacts.put(type, value);
    }

    public void addSection(final SectionType type, final Section section) {
        sections.put(type, section);
    }
}
