package toodoo.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;

import toodoo.exceptions.EmptyTaskListException;
import toodoo.tasks.Deadline;
import toodoo.tasks.Event;
import toodoo.tasks.Task;
import toodoo.tasks.TaskDateTime;

/**
 * Sorts the task list.
 */
public class TaskListSorter {
    /**
     * Sorts the task list according to specific criteria:
     * - Events and Deadlines come before ToDos
     * - Events/Deadlines are sorted by their from date/deadline respectively
     * - Tie-breaker for Events/Deadlines is description
     * - ToDos are sorted by description only
     *
     * @return A confirmation message indicating the tasks have been sorted.
     */
    public static String sortTasks(ArrayList<Task> tasks) throws EmptyTaskListException {
        if (tasks.isEmpty()) {
            throw new EmptyTaskListException();
        }

        // The code below was inspired by DeepSeek, with the prompt:
        // "How should I go about creating a custom sort in Java?"

        tasks.sort((task1, task2) -> {
            // Events and Deadlines come before ToDos
            boolean isTask1Timed = (task1 instanceof Event) || (task1 instanceof Deadline);
            boolean isTask2Timed = (task2 instanceof Event) || (task2 instanceof Deadline);

            if (isTask1Timed && !isTask2Timed) {
                return -1; // task1 comes before task2
            } else if (!isTask1Timed && isTask2Timed) {
                return 1; // task2 comes before task1
            } else if (isTask1Timed && isTask2Timed) {
                // Both are timed tasks - compare by date
                LocalDateTime date1 = TaskDateTime.getTaskDateTime(task1);
                LocalDateTime date2 = TaskDateTime.getTaskDateTime(task2);

                int dateComparison = date1.compareTo(date2);
                if (dateComparison != 0) {
                    return dateComparison;
                }
                // Tie-breaker: description
                return task1.getDescription().compareTo(task2.getDescription());
            } else {
                // Both are ToDos - compare by description only
                return task1.getDescription().compareTo(task2.getDescription());
            }
        });

        return "Your tasks have been sorted successfully!";
    }
}
