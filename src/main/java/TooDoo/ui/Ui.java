package toodoo.ui;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String CHAT_BOT_NAME = "TooDoo";

    /**
     * Prints the welcome message.
     */
    public String getWelcome() {
        return "How are you dooing! " 
                + CHAT_BOT_NAME + " at your service!\n"
                + "What would you like me too doo for you tooday?";
    }

    /**
     * Prints the exit message.
     */
    public String getExit() {
        return "Toodles! Visit me again soon!";
    }
}
