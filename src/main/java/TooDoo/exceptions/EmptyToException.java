package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to create an Event without specifying a to.
 */
public class EmptyToException extends Exception {
    public EmptyToException() {
        super("Goodness me! Does this event never end? Be sure too include the ending time.");
    }
}
