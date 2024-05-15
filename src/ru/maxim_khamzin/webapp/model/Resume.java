package ru.maxim_khamzin.webapp.model;

import java.util.Objects;
import java.util.UUID;

public class Resume {

    private final String uuid;

    public Resume() {
        this(UUID.randomUUID().toString());
    }

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

    public String getUuid() {
        return uuid;
    }
}
