package ru.maxim_khamzin.webapp.storage;

import ru.maxim_khamzin.webapp.exception.StorageException;
import ru.maxim_khamzin.webapp.model.Resume;
import ru.maxim_khamzin.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;
    private final ObjectStreamSerializer objectStreamSerializer;


    public PathStorage(String dir, ObjectStreamSerializer objectStreamSerializer) {
        directory = Paths.get(dir);
        this.objectStreamSerializer = objectStreamSerializer;

        Objects.requireNonNull(directory, "directory must not be null");

        if (!Files.isDirectory(directory) || !Files.isWritable(directory))
            throw new IllegalArgumentException("Is not a directory or it's not writable: %s"
                    .formatted(directory));
    }

    @Override
    protected Path getSearchKey(final String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(final Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doSave(final Resume resume, final Path path) {
        try {
            Files.createFile(path);
        }
        catch (IOException e) {
            throw new StorageException("The file couldn't be created using the path: %s"
                    .formatted(path), path.getFileName().toString(), e);
        }
        doUpdate(resume, path);
    }

    /**
     * new BufferedInputStream(new FileInputStream(path.toFile()) лучше переписать вот так ->
     * new BufferedInputStream(Files.newInputStream(path))
     */
    @Override
    protected Resume doGet(final Path path) {
        try (final var bufferedInputStream = new BufferedInputStream(Files.newInputStream(path))) {
            return objectStreamSerializer.doRead(bufferedInputStream);
        }
        catch (FileNotFoundException e) {
            throw new StorageException("The file was not found at the specified path: %s"
                    .formatted(path), getFileName(path), e);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read resume at specified path: %s"
                    .formatted(path), getFileName(path), e);
        }
    }

    @Override
    protected void doUpdate(final Resume resume, final Path path) {
        try (final var bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(path))) {
            objectStreamSerializer.doWrite(resume, bufferedOutputStream);
        }
        catch (IOException e) {
            throw new StorageException("Failed to write resume to file at specified path: %s"
                    .formatted(path), getFileName(path), e);
        }
    }

    @Override
    protected void doDelete(final Path path) {
        try {
            Files.delete(path);
        }
        catch (IOException e) {
            throw new StorageException("Failed to delete resume at specified path: %s"
                    .formatted(path), getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> doCopyGetAll() {
        return getFilesList()
                .map(this::doGet)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
//        try (Stream<Path> stream = Files.list(directory)) {
//            stream.forEach(this::doDelete);
//        }
//        catch (IOException e) {
//            throw new StorageException("Failed to delete resume to file: %s".formatted(directory), e);
//        }
        final var filesList = getFilesList();
        filesList.forEach(this::doDelete);
    }

    @Override
    public int size() {
//        try (Stream<Path> list = Files.list(directory)) {
//            return (int) list.count();
//        }
//        catch (IOException e) {
//            throw new StorageException("Failed to count the number of resumes", e);
//        }
        final var filesList = getFilesList();
        return (int) filesList.count();
    }

    private String getFileName(final Path path) {
        return path.getFileName().toString();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        }
        catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }
}
