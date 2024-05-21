package ru.maxim_khamzin.webapp.ui;

import ru.maxim_khamzin.webapp.model.Resume;
import ru.maxim_khamzin.webapp.storage.ArrayStorage;
import ru.maxim_khamzin.webapp.storage.MapStorage;
import ru.maxim_khamzin.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainArray {

    private final static Storage ARRAY_STORAGE = new MapStorage();

    public static void main(String[] args) throws IOException {
        final var reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;

        while (true) {
            System.out.print(
                    "Введите одну из команд -> " +
                    "(list | size | save fullName | delete param | get param | update param fullName | clear | exit): "
            );

            String[] params = reader.readLine().trim().toLowerCase().split(" ");

            if (params.length < 1 || params.length > 3) {
                System.out.println("Неверная команда.");
                continue;
            }

            String param = null;
            if (params.length > 1) {
                param = params[1].intern();
            }

            switch (params[0]) {
                case "list" -> printAll();
                case "size" -> System.out.println(ARRAY_STORAGE.size());
                case "save" -> {
                    resume = new Resume(param);
                    ARRAY_STORAGE.save(resume);
                    printAll();
                }
                case "update" -> {
                    resume = new Resume(param, params[2]);
                    ARRAY_STORAGE.update(resume);
                    printAll();
                }
                case "delete" -> {
                    ARRAY_STORAGE.delete(param);
                    printAll();
                }
                case "get" -> System.out.println(ARRAY_STORAGE.get(param));
                case "clear" -> {
                    ARRAY_STORAGE.clear();
                    printAll();
                }
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Неверная команда.");
            }
        }
    }

    static void printAll() {
        final var all = ARRAY_STORAGE.getAllSorted();

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                           "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                           "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                           "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                           "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                           "-------------------------------------------------------------");

        if (all.isEmpty()) {
            System.out.println("Empty");
        }
        else {
            for (Resume resume : all) {
                System.out.println(resume);
            }
        }

        System.out.println("-------------------------------------------------------------");
    }
}
