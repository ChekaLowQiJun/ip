package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to create an Event without specifying a from.
 */
public class EmptyFromException extends Exception {
    public EmptyFromException() {
        super("When does this event start? Maybe you should include it!");
    }
}
