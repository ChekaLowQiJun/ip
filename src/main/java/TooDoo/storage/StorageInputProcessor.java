package toodoo.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import toodoo.exceptions.StorageFormatException;
import toodoo.tasks.Deadline;
import toodoo.tasks.Event;
import toodoo.tasks.Task;
import toodoo.tasks.ToDo;

/**
 * Process the contents of the .txt storage file.
 */
public class StorageInputProcessor {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String TODO_REGEX = "^T \\| [ X] \\| .+?$";
    private static final String DEADLINE_REGEX = "^D \\| [ X] \\| .+? \\| \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";
    private static final String EVENT_REGEX = "^E \\| [ X] \\| .+? \\| \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2} \\|"
            + " \\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$";

    /**
     * Processes the lines of text from the .txt file return the corresponding task with the appropriate status.
     *
     * @param input A string representing a line from the .txt file.
     * @return The corresponding task with the appropriate status.
     * @throws StorageFormatException If the .txt file is not in the expected format.
     */
    public static Task processStorageInput(String input) throws StorageFormatException {
        assert input != null : "Input line should not be null";

        if (!input.matches(TODO_REGEX) && !input.matches(DEADLINE_REGEX) && !input.matches(EVENT_REGEX)) {
            throw new StorageFormatException();
        }

        String[] splitInputs = input.split(" \\| ");
        String typeOfTask = splitInputs[0];
        boolean isDone = splitInputs[1].equals("X") ? true : false;
        Task task;

        assert splitInputs.length >= 3 : "Input should have at least 3 parts";

        if (typeOfTask.equals("T")) {
            task = new ToDo(splitInputs[2]);
        } else if (typeOfTask.equals("D")) {
            task = new Deadline(splitInputs[2], LocalDateTime.parse(splitInputs[3], DATE_TIME_FORMATTER));
        } else {
            task = new Event(splitInputs[2], LocalDateTime.parse(splitInputs[3], DATE_TIME_FORMATTER),
                    LocalDateTime.parse(splitInputs[4], DATE_TIME_FORMATTER));
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

}
