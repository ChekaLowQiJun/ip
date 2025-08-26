package toodoo.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import toodoo.exceptions.EmptyDeadlineException;
import toodoo.exceptions.EmptyDescriptionException;
import toodoo.exceptions.EmptyFromException;
import toodoo.exceptions.EmptyToException;
import toodoo.exceptions.IndexDoesNotExistException;
import toodoo.exceptions.TaskAlreadyMarkedException;
import toodoo.exceptions.TaskAlreadyUnmarkedException;
import toodoo.exceptions.DateTimeConflictException;

import toodoo.tasks.Deadline;
import toodoo.tasks.Event;
import toodoo.tasks.Task;
import toodoo.tasks.ToDo;

/**
 * The TaskList is used by TooDoo to manage its task list.
 */
public class TaskList {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints the contents of the task list with proper formatting.
     */
    public void printList() {
        System.out.println(HORIZONTAL_LINE + "Presenting too you your task list:");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks a task in the task list at the specified index as done and prints the appropriate message.
     * 
     * @param index The index of the task in the task list that the user would like to mark.
     * @throws IndexDoesNotExistException If the index is out of bounds of the taskList.
     * @throws TaskAlreadyMarkedException If the task specified is already done.
     */
    public void mark(int index) throws IndexDoesNotExistException, TaskAlreadyMarkedException {
        if (index > taskList.size() - 1) {
            throw new IndexDoesNotExistException();
        }

        if (taskList.get(index).getIsDone()) {
            throw new TaskAlreadyMarkedException();
        }

        taskList.get(index).markAsDone();
        System.out.println(HORIZONTAL_LINE + "Good Job! You have completed this task:\n" 
                + taskList.get(index) + "\n" 
                + HORIZONTAL_LINE);
    }

    /**
     * Unmarks a task in the task list at the specified index and prints the appropriate message.
     * 
     * @param index The index of the task in the task list that the user would like to unmark.
     * @throws IndexDoesNotExistException If the index is out of bounds of the taskList.
     * @throws TaskAlreadyUnmarkedException If the task specified is already marked as not done.
     */
    public void unmark(int index) throws IndexDoesNotExistException, TaskAlreadyUnmarkedException {
        if (index > taskList.size() - 1) {
            throw new IndexDoesNotExistException();
        }

        if (!taskList.get(index).getIsDone()) {
            throw new TaskAlreadyUnmarkedException();
        }

        taskList.get(index).markAsNotDone();
        System.out.println(HORIZONTAL_LINE + "It's okay! Let's finish it another time!\n" 
                + taskList.get(index) + "\n" 
                + HORIZONTAL_LINE);
    }

    /**
     * Deletes a task in the task list at the specified index and prints the appropriate message.
     * 
     * @param index The index of the task in the task list that the user would like to delete.
     * @throws IndexDoesNotExistException If the index is out of bounds of the taskList.
     */
    public void delete(int index) throws IndexDoesNotExistException {
        if (index > taskList.size() - 1) {
            throw new IndexDoesNotExistException();
        }

        System.out.println(HORIZONTAL_LINE + "I have removed this task from the list for you:\n" 
                + taskList.get(index) + "\n" + "You now have " + (taskList.size() - 1) 
                + " tasks remaining in the list.\n" 
                + HORIZONTAL_LINE);
        taskList.remove(index);
    }

    /**
     * Adds a ToDo to the task list.
     * 
     * @param description The description of the ToDo.
     * @throws EmptyDescriptionException If the description of the ToDo is an empty string.
     */
    public void addToDo(String description) throws EmptyDescriptionException {
        taskList.add(new ToDo(description));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                + taskList.get(taskList.size() - 1) + "\n" 
                + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Adds a Deadline to the task list.
     * 
     * @param description The description of the Deadline.
     * @param by The deadline of the Deadline.
     * @throws EmptyDescriptionException If the description of the Deadline is an empty string.
     * @throws EmptyDeadlineException If the deadline of the Deadline is an empty string.
     */
    public void addDeadline(String description, String deadline) throws EmptyDescriptionException, EmptyDeadlineException {
        LocalDateTime byLocalDateTime = LocalDateTime.parse(deadline, DATE_TIME_FORMATTER);
        
        taskList.add(new Deadline(description, byLocalDateTime));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                + taskList.get(taskList.size() - 1) + "\n" 
                + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Adds an Event to the task list.
     * 
     * @param description The description of the Event.
     * @param from The from of the Event.
     * @param to The to of the Event.
     * @throws EmptyDescriptionException If the description of the Event is an empty string.
     * @throws EmptyFromException If the from of the Event is an empty string.
     * @throws EmptyToException If the to of the Event is an empty string.
     * @throws DateTimeConflictException If the to is before the from.
     */
    public void addEvent(String description, String from, String to) throws EmptyDescriptionException, 
            EmptyFromException, EmptyToException, DateTimeConflictException {
        LocalDateTime fromLocalDateTime = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        LocalDateTime toLocalDateTime = LocalDateTime.parse(to, DATE_TIME_FORMATTER);

        if (toLocalDateTime.isBefore(fromLocalDateTime)) {
            throw new DateTimeConflictException();
        }

        taskList.add(new Event(description, fromLocalDateTime, toLocalDateTime));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                + taskList.get(taskList.size() - 1) + "\n" 
                + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Returns the task list.
     * 
     * @return The task list of TooDoo.
     */
    public ArrayList<Task> getArrayList() {
        return taskList;
    }

    /**
     * Prints the Tasks in the tasklist that contains the regex in their description.
     * 
     * @param regex A regular expression used to find Tasks by their description.
     */
    public void find(String regex) {
        
        ArrayList<Task> temporaryTaskList = new ArrayList<>();

        for (int i=0;i < this.taskList.size();i++) {
            if (this.taskList.get(i).getDescription().contains(regex)) {
                temporaryTaskList.add(this.taskList.get(i));
            }
        }

        System.out.println(HORIZONTAL_LINE + "These are what you are looking for right:");

        for (int i = 0;i < temporaryTaskList.size();i++) {
            System.out.println((i + 1) + "." + temporaryTaskList.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
    }

}