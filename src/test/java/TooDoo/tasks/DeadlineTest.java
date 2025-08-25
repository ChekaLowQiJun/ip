package TooDoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] Dummy (by: OCTOBER 22 2025 10:45H)", new Deadline("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER)).toString());
        assertEquals("[D][ ] Dummy 2 (by: OCTOBER 22 2025 00:45H)", new Deadline("Dummy 2", LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER)).toString());
        assertEquals("[D][ ] Dummy Deadline (by: OCTOBER 22 2025 10:00H)", new Deadline("Dummy Deadline", LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER)).toString());
    }

    @Test
    public void getTaskStringTest(){
        assertEquals("D |   | Dummy | 2025-10-22 10:45", new Deadline("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER)).getTaskString());
        assertEquals("D |   | Dummy 2 | 2025-10-22 00:45", new Deadline("Dummy 2", LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER)).getTaskString());
        assertEquals("D |   | Dummy Deadline | 2025-10-22 10:00", new Deadline("Dummy Deadline", LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER)).getTaskString());
    }

    @Test
    public void markTest() {
        Deadline dummyTask = new Deadline("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER));
        assertEquals(false, dummyTask.getIsDone());
        dummyTask.markAsDone();
        assertEquals(true, dummyTask.getIsDone());
    }

    @Test
    public void unmarkTest() {
        Deadline dummyTask = new Deadline("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER));
        dummyTask.markAsDone();
        assertEquals(true, dummyTask.getIsDone());
        dummyTask.markAsNotDone();
        assertEquals(false, dummyTask.getIsDone());
    }

}

