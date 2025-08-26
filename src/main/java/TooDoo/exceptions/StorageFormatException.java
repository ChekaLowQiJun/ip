package toodoo.exceptions;

public class StorageFormatException extends Exception {
    public StorageFormatException() {
        super("Your .txt file seems to be using the wrong format! I will start a fresh one for you!");
    }
}
