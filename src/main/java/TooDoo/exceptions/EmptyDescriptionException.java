package TooDoo.exceptions;

/**
 * An Exception thrown when the user attempts to create a Task without specifying a description.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException() {
            super("Have you made a mistake? It would seem that your task's description is empty.");
        }
}
