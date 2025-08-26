package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to unmark an already unmarked task.
 */
public class TaskAlreadyUnmarkedException extends Exception {
    public TaskAlreadyUnmarkedException() {
        super("The task you specified is already marked as not done!");
    }
}
