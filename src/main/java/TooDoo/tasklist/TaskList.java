package toodoo.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import toodoo.exceptions.DateTimeConflictException;
import toodoo.exceptions.EmptyDeadlineException;
import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyToException;
import toodoo.exceptions.IndexDoesNotExistException;
import toodoo.exceptions.TaskAlreadyMarkedException;
import toodoo.exceptions.TaskAlreadyUnmarkedException;
import toodoo.tasks.Deadline;
import toodoo.tasks.Event;
import toodoo.tasks.Task;
import toodoo.tasks.ToDo;

/**
 * The TaskList is used by TooDoo to manage its task list.
 */
public class TaskList {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
     * Adds a ToDo to the task list.
     * @param description The description of the ToDo.
     * @return A confirmation message.
     * @throws EmptyDescriptionException If the description of the ToDo is an empty string.
     */
    public String addToDo(String description) throws EmptyDescriptionException {
        assert description != null : "Description should not be null";
        assert !description.trim().isEmpty() : "Description should not be empty";

        tasks.add(new ToDo(description));

        return "Aye aye captain! The following task has been added:\n"
                + tasks.get(tasks.size() - 1) + "\n"
                + "Now you have " + (tasks.size()) + " tasks in the list.";
    }

    /**
     * Adds a Deadline to the task list.
     * @param description The description of the Deadline.
     * @param deadline The deadline of the Deadline.
     * @return A confirmation message.
     * @throws EmptyDescriptionException If the description of the Deadline is an empty string.
     * @throws EmptyDeadlineException If the deadline of the Deadline is an empty string.
     */
    public String addDeadline(String description, String deadline) throws EmptyDescriptionException,
            EmptyDeadlineException {
        assert description != null : "Description should not be null";
        assert !description.trim().isEmpty() : "Description should not be empty";
        assert deadline != null : "Deadline should not be null";

        try {
            LocalDateTime byLocalDateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);

            tasks.add(new Deadline(description, byLocalDateTime));

            return "Aye aye captain! The following task has been added:\n"
                    + tasks.get(tasks.size() - 1) + "\n"
                    + "Now you have " + (tasks.size()) + " tasks in the list.";
        } catch (DateTimeParseException e) {
            return "When specifying a date and time, please use the following format yyyy-MM-dd HH:mm !"
                    + " to specify a date that exists";
        }
    }

    /**
     * Adds an Event to the task list.
     * @param description The description of the Event.
     * @param from The from of the Event.
     * @param to The to of the Event.
     * @return A confirmation message.
     * @throws EmptyDescriptionException If the description of the Event is an empty string.
     * @throws EmptyFromException If the from of the Event is an empty string.
     * @throws EmptyToException If the to of the Event is an empty string.
     * @throws DateTimeConflictException If the to is before the from.
     */
    public String addEvent(String description, String from, String to) throws EmptyDescriptionException,
            EmptyFromException, EmptyToException, DateTimeConflictException {
        assert description != null : "Description should not be null";
        assert !description.trim().isEmpty() : "Description should not be empty";
        assert from != null : "From time should not be null";
        assert to != null : "To time should not be null";

        try {
            LocalDateTime fromLocalDateTime = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
            LocalDateTime toLocalDateTime = LocalDateTime.parse(to, DATE_TIME_FORMATTER);

            if (toLocalDateTime.isBefore(fromLocalDateTime)) {
                throw new DateTimeConflictException();
            }

            tasks.add(new Event(description, fromLocalDateTime, toLocalDateTime));
            return "Aye aye captain! The following task has been added:\n"
                    + tasks.get(tasks.size() - 1) + "\n"
                    + "Now you have " + (tasks.size()) + " tasks in the list.";
        } catch (DateTimeParseException e) {
            return "When specifying a date and time, please use the following format yyyy-MM-dd HH:mm !"
                    + " to specify a date that exists";
        }
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
        List<Task> matchingTasks = tasks.stream()
            .filter(task -> task.getDescription().contains(regex))
            .collect(Collectors.toList());

        return formatFindResults(matchingTasks, regex);
    }

    /**
     * Formats the results of the find command.
     * @param matchingTasks A list of tasks that match the regex.
     * @param regex The regex used to find matching tasks.
     * @return A formatted string of the find results.
     */
    private String formatFindResults(List<Task> matchingTasks, String regex) {
        if (matchingTasks.isEmpty()) {
            return "No tasks found matching: " + regex;
        }

        StringBuilder result = new StringBuilder("These are what you are looking for right:\n");
        IntStream.range(0, matchingTasks.size())
            .forEach(i -> result.append((i + 1) + "." + matchingTasks.get(i) + "\n"));

        return result.toString();
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

    /**
     * Sorts the task list according to specific criteria:
     * - Events and Deadlines come before ToDos
     * - Events/Deadlines are sorted by their from date/deadline respectively
     * - Tie-breaker for Events/Deadlines is description
     * - ToDos are sorted by description only
     * @return A confirmation message indicating the tasks have been sorted.
     */
    public String sortTasks() {
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
                LocalDateTime date1 = getTaskDateTime(task1);
                LocalDateTime date2 = getTaskDateTime(task2);

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

    /**
     * Helper method to extract the relevant date from a task
     * For Events: returns the from date
     * For Deadlines: returns the deadline
     * For ToDos: returns a very distant future date (so they sort to the end)
     * @param task The task from which to extract the date
     * @return The relevant LocalDateTime for sorting
     */
    private LocalDateTime getTaskDateTime(Task task) {
        if (task instanceof Event) {
            return ((Event) task).getFrom();
        } else if (task instanceof Deadline) {
            return ((Deadline) task).getDeadline();
        } else {
            // For ToDos, return a date far in the future so they sort to the end
            return LocalDateTime.MAX;
        }
    }
}
