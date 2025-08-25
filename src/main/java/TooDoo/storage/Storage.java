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

public class Storage {
    private static String filePath;
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

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

    public ArrayList<Task> loadList() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();

        File taskListFile = new File(Storage.filePath); 
        Scanner taskListScanner = new Scanner(taskListFile);

        while (taskListScanner.hasNext()) {
            taskList.add(processStorageInput(taskListScanner.nextLine()));
        }

        taskListScanner.close();
        return taskList;

    }

    public static Task processStorageInput(String input) {
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
