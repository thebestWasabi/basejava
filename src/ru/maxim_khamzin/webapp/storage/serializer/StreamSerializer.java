package ru.maxim_khamzin.webapp.storage.serializer;

import ru.maxim_khamzin.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface StreamSerializer {

    void write(final Resume resume, OutputStream outputStream) throws IOException;

    Resume read(final InputStream inputStream) throws IOException;
}
