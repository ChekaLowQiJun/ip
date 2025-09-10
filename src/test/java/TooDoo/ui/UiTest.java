package toodoo.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    
    Ui ui = new Ui();

    @Test
    public void getWelcomeTest() {
        assertEquals(ui.getWelcome(),
                "How are you dooing! "
                + "TooDoo at your service!\n"
                + "What would you like me too doo for you tooday?");
    }

    @Test
    public void getExitTest() {
        assertEquals(ui.getExit(), "Toodles! Visit me again soon!");
    }
}
