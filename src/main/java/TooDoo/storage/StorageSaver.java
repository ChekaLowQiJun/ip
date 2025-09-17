package toodoo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import toodoo.tasklist.TaskList;
import toodoo.tasks.Task;

/**
 * Saves the current task list in the .txt storage file.
 */
public class StorageSaver {

    /**
     * Saves the current task list and writes to the .txt file specified in the constructor if the file exists.
     *
     * @param taskList A TaskList object used to manage TooDoo's task list.
     * @return A confirmation message indicating the save result.
     */
    public static String saveList(TaskList taskList, String filePath) {
        assert taskList != null : "TaskList should not be null";

        try {
            if (new File(filePath).exists()) {
                FileWriter fw = new FileWriter(filePath);
                StringBuilder taskString = new StringBuilder();
                ArrayList<Task> tasks = taskList.getArrayList();

                for (int i = 0; i < tasks.size(); i++) {
                    taskString.append(tasks.get(i).getTaskString()).append("\n");
                }
                fw.write(taskString.toString());
                fw.close();
                return "Your task list has been saved successfully!";
            } else {
                new File(filePath);

                FileWriter fw = new FileWriter(filePath);
                StringBuilder taskString = new StringBuilder();
                ArrayList<Task> tasks = taskList.getArrayList();

                for (int i = 0; i < tasks.size(); i++) {
                    taskString.append(tasks.get(i).getTaskString()).append("\n");
                }
                fw.write(taskString.toString());
                fw.close();

                return "Oh noo unfortunately there was an error with saving your task list...apologies!"
                        + "I will create a new .txt for you now!";
            }
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
