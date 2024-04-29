package ru.maxim_khamzin.webapp.ui;

import ru.maxim_khamzin.webapp.model.Resume;
import ru.maxim_khamzin.webapp.storage.ArrayStorage;
import ru.maxim_khamzin.webapp.storage.SortedArrayStorage;
import ru.maxim_khamzin.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainArray {

    private final static Storage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;

        while (true) {
            System.out.print("Введите одну из команд - (list | size | save uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");

            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }

            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }

            switch (params[0]) {
                case "list" -> printAll();
                case "size" -> System.out.println(ARRAY_STORAGE.size());
                case "save" -> {
                    resume = new Resume(uuid);
                    ARRAY_STORAGE.save(resume);
                    printAll();
                }
                case "delete" -> {
                    ARRAY_STORAGE.delete(uuid);
                    printAll();
                }
                case "get" -> System.out.println(ARRAY_STORAGE.get(uuid));
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
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("----------------------------");

        if (all.length == 0) {
            System.out.println("Empty");
        }
        else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }

        System.out.println("----------------------------");
    }

}
