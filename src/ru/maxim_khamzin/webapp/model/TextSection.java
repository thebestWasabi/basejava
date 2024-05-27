package ru.maxim_khamzin.webapp.model;

import java.io.Serial;
import java.util.Objects;

public class TextSection extends Section {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String content;

    public TextSection(final String content) {
        Objects.requireNonNull(content, "Content must not be null");
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextSection {content='%s'}".formatted(content);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TextSection that = (TextSection) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(content);
    }

    public String getContent() {
        return content;
    }
}
