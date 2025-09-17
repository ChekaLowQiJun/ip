package toodoo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import toodoo.exceptions.StorageFormatException;
import toodoo.tasks.Task;

/**
 * Loads the task list from the .txt storage file.
 */
public class StorageLoader {

    /**
     * Loads the task list from the .txt file specified in the constructor if it exists and returns it.
     *
     * @return The task list from the .txt file.
     * @throws FileNotFoundException If the file specified in the constructor does not exist.
     * @throws StorageFormatException If the .txt file is not in the expected format.
     */
    public static ArrayList<Task> loadList(String filePath) throws FileNotFoundException, StorageFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        File taskListFile = new File(filePath);

        Scanner taskListScanner = new Scanner(taskListFile);

        while (taskListScanner.hasNext()) {
            String input = taskListScanner.nextLine();

            try {
                Task processedStorageInput = StorageInputProcessor.processStorageInput(input);
                tasks.add(processedStorageInput);
            } catch (DateTimeParseException e) {
                System.out.println("Please check the date time format of this line:\n"
                        + input);
                continue;
            }
        }

        taskListScanner.close();
        return tasks;
    }

}
