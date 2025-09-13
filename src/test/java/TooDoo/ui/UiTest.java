package toodoo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    private Ui ui = new Ui();

    @Test
    public void getWelcomeTest() {
        assertEquals("How are you dooing! "
                + "TooDoo at your service!\n"
                + "What would you like me too doo for you tooday?", ui.getWelcome());
    }

    @Test
    public void getExitTest() {
        assertEquals("Toodles! Visit me again soon!", ui.getExit());
    }
}
