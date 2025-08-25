package TooDoo.exceptions;

public class EmptyRegexException extends Exception {
    public EmptyRegexException() {
        super("How am I going to find it if you don't give me any hints :\"(");
    }
}
