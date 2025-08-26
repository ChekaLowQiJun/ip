package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to mark an already marked task.
 */
public class TaskAlreadyMarkedException extends Exception {
    public TaskAlreadyMarkedException() {
        super("The task you specified is already marked as done!");
    }
}
