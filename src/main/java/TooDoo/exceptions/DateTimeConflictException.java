package toodoo.exceptions;

/**
 * An Exception thrown when the user attempts to create an Event with a to earlier than the from.
 */
public class DateTimeConflictException extends Exception {
    public DateTimeConflictException() {
        super("Are you a time traveller...why is your too at a time before your from?");
    }
}
