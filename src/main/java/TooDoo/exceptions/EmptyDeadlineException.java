package TooDoo.exceptions;

/**
 * An Exception thrown when the user attempts to create a Deadline without specifying a deadline.
 */
public class EmptyDeadlineException extends Exception {
    public EmptyDeadlineException() {
        super("Oh dear...if your task has no deadline, maybe it should be a toodoo.");
    }
}
