package ru.maxim_khamzin.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String title;
    private final String description;

    public Organization(final String name, final String url,
                        final LocalDate startDate, final LocalDate endDate,
                        final String title, final String description)
    {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(title, "title must not be null");

        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organization {homePage=%s, startDate=%s, endDate=%s, title='%s', description='%s'}"
                .formatted(homePage, startDate, endDate, title, description);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage)
               && Objects.equals(startDate, that.startDate)
               && Objects.equals(endDate, that.endDate)
               && Objects.equals(title, that.title)
               && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, title, description);
    }
}
