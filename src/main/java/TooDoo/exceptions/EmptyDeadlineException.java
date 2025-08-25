package toodoo.exceptions;

public class EmptyDeadlineException extends Exception {
    public EmptyDeadlineException() {
        super("Oh dear...if your task has no deadline, maybe it should be a toodoo.");
    }
}
