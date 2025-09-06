package toodoo.exceptions;

/**
 * Exception thrown when the regex provided is empty.
 */
public class EmptyRegexException extends Exception {
    public EmptyRegexException() {
        super("How am I going to find it if you don't give me any hints :\"(");
    }
}
