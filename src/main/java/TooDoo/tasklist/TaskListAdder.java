package toodoo.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import toodoo.exceptions.DateTimeConflictException;
import toodoo.exceptions.EmptyDeadlineException;
import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyToException;
import toodoo.tasks.Deadline;
import toodoo.tasks.Event;
import toodoo.tasks.Task;
import toodoo.tasks.ToDo;

public class TaskListAdder {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    /**
     * Adds a ToDo to the task list.
     * @param description The description of the ToDo.
     * @return A confirmation message.
     * @throws EmptyDescriptionException If the description of the ToDo is an empty string.
     */
    public static String addToDo(ArrayList<Task> tasks,String description) throws EmptyDescriptionException {
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
    public static String addDeadline(ArrayList<Task> tasks,String description, String deadline) throws EmptyDescriptionException,
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
    public static String addEvent(ArrayList<Task> tasks, String description, String from, String to) throws EmptyDescriptionException,
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

}
