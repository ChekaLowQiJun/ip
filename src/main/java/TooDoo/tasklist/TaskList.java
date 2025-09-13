package toodoo.tasklist;

import java.util.ArrayList;

import toodoo.exceptions.DateTimeConflictException;
import toodoo.exceptions.EmptyDeadlineException;
import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyTaskListException;
import toodoo.exceptions.EmptyToException;
import toodoo.exceptions.IndexDoesNotExistException;
import toodoo.exceptions.TaskAlreadyMarkedException;
import toodoo.exceptions.TaskAlreadyUnmarkedException;
import toodoo.tasks.Task;

/**
 * The TaskList is used by TooDoo to manage its task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an existing ArrayList of Tasks.
     * @param taskList An existing ArrayList of Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null : "Task list should not be null";

        this.tasks = taskList;
    }

    public String addToDo(String description) throws EmptyDescriptionException {
        return TaskListAdder.addToDo(tasks, description);
    }

    public String addDeadline(String description, String deadline) throws EmptyDescriptionException,
            EmptyDeadlineException {
        return TaskListAdder.addDeadline(tasks, description, deadline);
    }

    public String addEvent(String description, String from, String to) throws EmptyDescriptionException,
            EmptyFromException, EmptyToException, DateTimeConflictException {
        return TaskListAdder.addEvent(tasks, description, from, to);
    }   

    /**
     * Marks a task in the task list at the specified index as done and prints the appropriate message.
     * @param index The index of the task in the task list that the user would like to mark.
     * @return A confirmation message.
     * @throws IndexDoesNotExistException If the index is out of bounds of the taskList.
     * @throws TaskAlreadyMarkedException If the task specified is already done.
     */
    public String mark(int index) throws IndexDoesNotExistException, TaskAlreadyMarkedException {
        assert index >= 0 : "Index should be non-negative";
        assert index < tasks.size() : "Index should be within bounds";

        validateIndex(index);

        if (tasks.get(index).getIsDone()) {
            throw new TaskAlreadyMarkedException();
        }

        tasks.get(index).markAsDone();

        return "Good Job! You have completed this task:\n"
                + tasks.get(index);
    }

    /**
     * Unmarks a task in the task list at the specified index and prints the appropriate message.
     * @param index The index of the task in the task list that the user would like to unmark.
     * @return A confirmation message.
     * @throws IndexDoesNotExistException If the index is out of bounds of the taskList.
     * @throws TaskAlreadyUnmarkedException If the task specified is already marked as not done.
     */
    public String unmark(int index) throws IndexDoesNotExistException, TaskAlreadyUnmarkedException {
        assert index >= 0 : "Index should be non-negative";
        assert index < tasks.size() : "Index should be within bounds";

        validateIndex(index);

        if (!tasks.get(index).getIsDone()) {
            throw new TaskAlreadyUnmarkedException();
        }

        tasks.get(index).markAsNotDone();

        return "It's okay! Let's finish it another time!\n"
                + tasks.get(index);
    }

    /**
     * Deletes a task in the task list at the specified index and prints the appropriate message.
     * @param index The index of the task in the task list that the user would like to delete.
     * @return A confirmation message.
     * @throws IndexDoesNotExistException If the index is out of bounds of the taskList.
     */
    public String delete(int index) throws IndexDoesNotExistException {
        assert index >= 0 : "Index should be non-negative";
        assert index < tasks.size() : "Index should be within bounds";

        validateIndex(index);

        String message = "I have removed this task from the list for you:\n"
                + tasks.get(index) + "\n" + "You now have " + (tasks.size() - 1)
                + " tasks remaining in the list.";
        tasks.remove(index);

        return message;
    }

    /**
     * Returns the task list.
     * @return The task list of TooDoo.
     */
    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    /**
     * Prints the Tasks in the tasklist that contains the regex in their description.
     * @param regex A regular expression used to find Tasks by their description.
     * @return A string containing matching tasks.
     */
    public String find(String regex) {
        return TaskListFinder.find(regex, tasks);
    }

    /**
     * Returns a string representation of the task list.
     * @return A formatted string of all tasks.
     */
    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("Presenting too you your task list: \n");

        for (int i = 0; i < tasks.size(); i++) {
            listString.append((i + 1) + "." + tasks.get(i) + "\n");
        }

        return listString.toString();
    }

    private void validateIndex(int index) throws IndexDoesNotExistException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexDoesNotExistException();
        }
    }

    public String sortTasks() throws EmptyTaskListException {
        return TaskListSorter.sortTasks(tasks);
    }
}
