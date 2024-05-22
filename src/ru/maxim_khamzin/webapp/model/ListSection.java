package ru.maxim_khamzin.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private final List<String> items;

    public ListSection(final List<String> items) {
        Objects.requireNonNull(items, "items must not be null");
        this.items = items;
    }

    @Override
    public String toString() {
        return "ListSection {items=%s}".formatted(items);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ListSection that = (ListSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(items);
    }

    public List<String> getItems() {
        return items;
    }
}
