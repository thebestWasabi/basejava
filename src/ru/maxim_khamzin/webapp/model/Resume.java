package ru.maxim_khamzin.webapp.model;

import java.util.Objects;

public class Resume implements Comparable<Resume> {

    private final String uuid;

    public Resume(final String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(final Resume o) {
        return String.CASE_INSENSITIVE_ORDER.compare(uuid, o.uuid);
    }

    public String getUuid() {
        return uuid;
    }
}
