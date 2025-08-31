package toodoo.ui;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String CHAT_BOT_NAME = "TooDoo";

    /**
     * Returns the welcome message for the user.
     *
     * @return The welcome message string.
     */
    public String getWelcome() {
        return "How are you dooing! " 
                + CHAT_BOT_NAME + " at your service!\n"
                + "What would you like me too doo for you tooday?";
    }

    /**
     * Returns the exit message for the user.
     *
     * @return The exit message string.
     */
    public String getExit() {
        return "Toodles! Visit me again soon!";
    }
}
