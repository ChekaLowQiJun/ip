package toodoo.exceptions;

/**
 * An Exception thrown if the Storage class attempts to load a .txt file that is not in the expected format.
 */
public class StorageFormatException extends Exception {
    public StorageFormatException() {
        super("Your .txt file seems too be using the wrong format! I will start a fresh one for you!");
    }
}
