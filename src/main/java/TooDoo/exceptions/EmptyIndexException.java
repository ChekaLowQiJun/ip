package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to delete, mark or unmark a Task without specifying an index.
 */
public class EmptyIndexException extends Exception {
    public EmptyIndexException() {
        super("You must have forgotten too provide an integer representing the task's index -_-");
    }
}
