package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to delete, mark and unmark using an index that does not exist.
 */
public class IndexDoesNotExistException extends Exception {
    public IndexDoesNotExistException() {
        super("The task number that you specified does not exist :P");
    }
}
