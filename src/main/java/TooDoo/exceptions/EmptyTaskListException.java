package toodoo.exceptions;

/**
 * Exception thrown when the regex provided is empty.
 */
public class EmptyTaskListException extends Exception {
    public EmptyTaskListException() {
        super("Can't be sorting an empty task list now, can we?");
    }
}
