package toodoo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

import toodoo.tasklist.TaskList;
import toodoo.tasks.Deadline;
import toodoo.tasks.Event;
import toodoo.tasks.Task;
import toodoo.tasks.ToDo;

import toodoo.exceptions.StorageFormatException;

/**
 * The Storage is used by TooDoo to load and make local saves of the task list.
 */
public class Storage {
    private static String filePath;
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs the Storage object using the filePath of the existing task list.
     * 
     * @param filePath File path to the existing .txt file containing the task list.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Saves the current task list and writes to the .txt file specified in the constructor if the file exists.
     * 
     * @param taskList A TaskList object used to manage TooDoo's task list.
     */
    public void saveList(TaskList taskList) {
        try {
            if (new File(Storage.filePath).exists()) {
                FileWriter fw = new FileWriter(Storage.filePath);
                StringBuilder tasks = new StringBuilder();
                ArrayList<Task> taskArrayList = taskList.getArrayList();

                for (int i = 0; i < taskArrayList.size(); i++) {
                    tasks.append(taskArrayList.get(i).getTaskString()).append("\n");
                }
                fw.write(tasks.toString());
                fw.close();
            } else {
                System.out.print(HORIZONTAL_LINE 
                        + "Oh no unfortunately there was an error with saving your Task List...apologies! \n"
                        + HORIZONTAL_LINE);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the task list from the .txt file specified in the constructor if it exists and returns it.
     * 
     * @return The task list from the .txt file.
     * @throws FileNotFoundException If the file specified in the constructor does not exist.
     */
    public ArrayList<Task> loadList() throws FileNotFoundException, StorageFormatException {
        ArrayList<Task> taskList = new ArrayList<>();

        File taskListFile = new File(Storage.filePath); 
        Scanner taskListScanner = new Scanner(taskListFile);

        while (taskListScanner.hasNext()) {
            taskList.add(processStorageInput(taskListScanner.nextLine()));
        }

        taskListScanner.close();
        return taskList;

    }

    /**
     * Processes the lines of text from the .txt file return the corresponding task with the appropriate status.
     * 
     * @param input A string representing a line from the .txt file. 
     * @return The corresponding task with the appropriate status.
     */
    public static Task processStorageInput(String input) throws StorageFormatException {
        String regexT = "^T \\|   \\| .+?$";
        String regexD = "^D \\|   \\| .+? \\| \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";
        String regexE = "^E \\|   \\| .+? \\| \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2} \\| \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";

        if (!input.matches(regexT) && !input.matches(regexD) && !input.matches(regexE)) {
            throw new StorageFormatException();
        }

        String[] splitInput = input.split(" \\| ");
        String typeOfTask = splitInput[0];
        boolean isDone = splitInput[1].equals("X") ? true : false;
        Task task;

        if (typeOfTask.equals("T")) {
            task = new ToDo(splitInput[2]);
        } else if (typeOfTask.equals("D")) {
            task = new Deadline(splitInput[2], LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER));
        } else {
            task = new Event(splitInput[2], LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER), 
                    LocalDateTime.parse(splitInput[4], DATE_TIME_FORMATTER));
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
