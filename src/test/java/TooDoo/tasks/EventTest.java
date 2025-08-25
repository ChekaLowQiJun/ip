package TooDoo.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] Dummy (from: OCTOBER 22 2025 10:45H to: OCTOBER 22 2025 20:45H)", new Event("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER)).toString());
        assertEquals("[E][ ] Dummy 2 (from: OCTOBER 22 2025 00:45H to: OCTOBER 22 2025 10:10H)", new Event("Dummy 2", LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 10:10", DATE_TIME_FORMATTER)).toString());
        assertEquals("[E][ ] Dummy Event (from: OCTOBER 22 2025 10:00H to: OCTOBER 22 2025 20:00H)", new Event("Dummy Event", LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 20:00", DATE_TIME_FORMATTER)).toString());
    }

    @Test
    public void getTaskStringTest(){
        assertEquals("E |   | Dummy | 2025-10-22 10:45 | 2025-10-22 20:45", new Event("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER)).getTaskString());
        assertEquals("E |   | Dummy 2 | 2025-10-22 00:45 | 2025-10-22 10:10", new Event("Dummy 2", LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 10:10", DATE_TIME_FORMATTER)).getTaskString());
        assertEquals("E |   | Dummy Event | 2025-10-22 10:00 | 2025-10-22 20:00", new Event("Dummy Event", LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 20:00", DATE_TIME_FORMATTER)).getTaskString());
    }

    @Test
    public void markTest() {
        Event dummyTask = new Event("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER));
        assertEquals(false, dummyTask.getIsDone());
        dummyTask.markAsDone();
        assertEquals(true, dummyTask.getIsDone());
    }

    @Test
    public void unmarkTest() {
        Event dummyTask = new Event("Dummy", LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER), LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER));
        dummyTask.markAsDone();
        assertEquals(true, dummyTask.getIsDone());
        dummyTask.markAsNotDone();
        assertEquals(false, dummyTask.getIsDone());
    }

}

