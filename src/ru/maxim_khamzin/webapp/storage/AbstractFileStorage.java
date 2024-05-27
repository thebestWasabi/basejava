package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private final File directory;

    protected abstract void doWrite(final Resume resume, final OutputStream outputStream) throws IOException;

    protected abstract Resume doRead(final InputStream inputStream) throws IOException;


    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");

        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("%s is not a directory".formatted(directory.getAbsolutePath()));
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException("%s is not readable/writeable".formatted(directory.getAbsolutePath()));
        }

        this.directory = directory;
    }

    @Override
    protected File getSearchKey(final String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(final File file) {
        return file.exists();
    }

    @Override
    protected void doSave(final Resume resume, final File file) {
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            throw new StorageException("Couldn't create file: %s".formatted(file.getAbsolutePath()), file.getName(), e);
        }
        doUpdate(resume, file);
    }

    @Override
    protected Resume doGet(final File file) {
        try {
            return doRead(new BufferedInputStream(new FileInputStream(file)));
        }
        catch (IOException e) {
            throw new StorageException("Can't read file: %s".formatted(file.getAbsolutePath()), file.getName(), e);
        }
    }

    @Override
    protected List<Resume> doCopyGetAll() {
        final var files = directory.listFiles();

        if (files == null) {
            throw new StorageException("Directory read error");
        }

        List<Resume> result = new ArrayList<>(files.length);
        for (File file : files) {
            result.add(doGet(file));
        }
        return result;
    }

    @Override
    protected void doUpdate(final Resume resume, final File file) {
        try {
            doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        }
        catch (IOException e) {
            throw new StorageException("File write error: %s".formatted(file.getAbsolutePath()), resume.getUuid(), e);
        }
    }

    @Override
    protected void doDelete(final File file) {
        if (!file.delete()) {
            throw new StorageException("Error: file delete", file.getName());
        }
    }

    @Override
    public void clear() {
        final var files = directory.listFiles();

        if (files != null) {
            for (final File file : files) {
                doDelete(file);
            }
        }
    }

    @Override
    public int size() {
        final var list = directory.list();

        if (list == null) {
            throw new StorageException("Directory read error");
        }
        return list.length;
    }

}
