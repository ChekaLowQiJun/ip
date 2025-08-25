package toodoo.exceptions;

public class EmptyToException extends Exception {
    public EmptyToException() {
        super("Goodness me! Does this event never end? Be sure to include the ending time.");
    }
}
