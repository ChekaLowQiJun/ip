package TooDoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] Dummy", new ToDo("Dummy").toString());
        assertEquals("[T][ ] Dummy 2", new ToDo("Dummy 2").toString());
        assertEquals("[T][ ] Dummy To Do", new ToDo("Dummy To Do").toString());
    }

    @Test
    public void getTaskStringTest(){
        assertEquals("T |   | Dummy", new ToDo("Dummy").getTaskString());
        assertEquals("T |   | Dummy 2", new ToDo("Dummy 2").getTaskString());
        assertEquals("T |   | Dummy To Do", new ToDo("Dummy To Do").getTaskString());
    }

}

