package toodoo.ui;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String CHAT_BOT_NAME = "TooDoo";
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    /**
     * Prints the welcome message.
     */
    public void getWelcome() {
        System.out.println( HORIZONTAL_LINE + "How are you dooing! " 
                + CHAT_BOT_NAME + " at your service!\n"
                + "What would you like me too doo for you tooday?\n"
                +  HORIZONTAL_LINE);
    }

    /**
     * Prints the exit message.
     */
    public void getExit() {
        System.out.println(HORIZONTAL_LINE + "Toodles! Visit me again soon!\n" 
                + HORIZONTAL_LINE);
    }

    /**
     * Prints a message with horizontal lines above and below the message.
     * 
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE + message + "\n" 
                                    + HORIZONTAL_LINE);
    }
}
