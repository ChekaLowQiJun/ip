package Exceptions;

public class UnknownKeywordException extends Exception {
    public UnknownKeywordException(String message) {
            super("Hmmmmm I doo not recognise this word: " + message);
        }
}
