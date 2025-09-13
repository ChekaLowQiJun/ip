package toodoo.storage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import toodoo.exceptions.StorageFormatException;
import toodoo.tasklist.TaskList;
import toodoo.tasks.Task;

/**
 * The Storage is used by TooDoo to load and make local saves of the task list.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs the Storage object using the filePath of the existing task list.
     * @param filePath File path to the existing .txt file containing the task list.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.trim().isEmpty() : "File path should not be empty";

        this.filePath = filePath;
    }

    public String saveList(TaskList taskList) {
        return StorageSaver.saveList(taskList, filePath);
    }

    public ArrayList<Task> loadList() throws FileNotFoundException, StorageFormatException {
        return StorageLoader.loadList(filePath);
    }

}
