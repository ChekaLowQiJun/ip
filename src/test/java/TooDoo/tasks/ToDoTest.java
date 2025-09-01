package toodoo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toStringTest() {
        assertEquals("[T][ ] Dummy", new ToDo("Dummy").toString()); // Description with one word
        assertEquals("[T][ ] Dummy two", new ToDo("Dummy two").toString()); // Description with two words
        assertEquals("[T][ ] Dummy To Do", new ToDo("Dummy To Do").toString()); // Description with three words
    }

    @Test
    public void getTaskStringTest() {
        assertEquals("T |   | Dummy", new ToDo("Dummy").getTaskString()); // Description with one word
        assertEquals("T |   | Dummy two", new ToDo("Dummy two").getTaskString()); // Description with two words
        assertEquals("T |   | Dummy To Do", new ToDo("Dummy To Do").getTaskString()); // Description with three words
    }

    @Test
    public void markTest() {
        ToDo dummyToDo = new ToDo("Dummy");
        assertEquals(false, dummyToDo.getIsDone()); // Initial state of ToDo
        dummyToDo.markAsDone();
        assertEquals(true, dummyToDo.getIsDone()); // Marking unmaked ToDo
    }

    @Test
    public void unmarkTest() {
        ToDo dummyToDo = new ToDo("Dummy");
        dummyToDo.markAsDone();
        assertEquals(true, dummyToDo.getIsDone()); // Marking unmarked ToDo
        dummyToDo.markAsNotDone();
        assertEquals(false, dummyToDo.getIsDone()); // Unmarking marked ToDo
    }

}

