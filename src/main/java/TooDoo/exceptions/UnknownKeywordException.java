package toodoo.exceptions;

/**
 * An Exception thrown when the Parser encounters an unknown Keyword.
 */
public class UnknownKeywordException extends Exception {
    public UnknownKeywordException(String message) {
            super("Hmmmmm I doo not recognise this word: " + message);
        }
}
