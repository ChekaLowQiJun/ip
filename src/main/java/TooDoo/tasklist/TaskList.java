package TooDoo.tasklist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import TooDoo.exceptions.EmptyDeadlineException;
import TooDoo.exceptions.EmptyDescriptionException;
import TooDoo.exceptions.EmptyFromException;
import TooDoo.exceptions.EmptyToException;
import TooDoo.tasks.Deadline;
import TooDoo.tasks.Event;
import TooDoo.tasks.Task;
import TooDoo.tasks.ToDo;

public class TaskList {

    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    };

    public void printList() {
        System.out.println(HORIZONTAL_LINE + "Presenting too you your task list:");

        for (int i = 0;i < this.taskList.size();i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public void mark(int index) {
        taskList.get(index).markAsDone();
        System.out.println(HORIZONTAL_LINE + "Good Job! You have completed this task:\n" 
                            + taskList.get(index) + "\n" 
                            + HORIZONTAL_LINE);
    }

    public void unmark(int index) {
        taskList.get(index).markAsNotDone();
        System.out.println(HORIZONTAL_LINE + "It's okay! Let's finish it another time!\n" 
                            + taskList.get(index) + "\n" 
                            + HORIZONTAL_LINE);
    }

    public void delete(int index) {
        System.out.println(HORIZONTAL_LINE + "I have removed this task from the list for you:\n" 
                            + taskList.get(index) + "\n" + "You now have " + (taskList.size() - 1) + " tasks remaining in the list.\n" 
                            + HORIZONTAL_LINE);
        taskList.remove(index);
    }

    public void addToDo(String description) throws EmptyDescriptionException {
        taskList.add(new ToDo(description));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList.get(taskList.size() - 1) + "\n" 
                            + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
    }

    public void addDeadline(String description, String by) throws EmptyDescriptionException, EmptyDeadlineException {
        
        LocalDateTime byLocalDateTime = LocalDateTime.parse(by, DATE_TIME_FORMATTER);
        
        taskList.add(new Deadline(description, byLocalDateTime));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList.get(taskList.size() - 1) + "\n" 
                            + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
    }

    public void addEvent(String description, String from, String to) throws EmptyDescriptionException, EmptyFromException, EmptyToException {

        LocalDateTime fromLocalDateTime = LocalDateTime.parse(from, DATE_TIME_FORMATTER);
        LocalDateTime toLocalDateTime = LocalDateTime.parse(to, DATE_TIME_FORMATTER);

        taskList.add(new Event(description, fromLocalDateTime, toLocalDateTime));
        System.out.println(HORIZONTAL_LINE + "Aye aye captain! The following task has been added: \n" 
                            + taskList.get(taskList.size() - 1) + "\n" 
                            + "Now you have " + (taskList.size()) + " tasks in the list.\n"
                            + HORIZONTAL_LINE);
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

}