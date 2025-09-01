package toodoo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void toStringTest() {
        Event dummyEvent = new Event("Dummy", 
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER), 
                LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER));
        Event dummyTwoEvent = new Event("Dummy twp", 
                LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER), 
                LocalDateTime.parse("2025-10-22 10:10", DATE_TIME_FORMATTER));
        Event dummyTwoTwoEvent = new Event("Dummy two two", 
                LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER), 
                LocalDateTime.parse("2025-10-22 20:00", DATE_TIME_FORMATTER));

        assertEquals("[E][ ] Dummy (from: OCTOBER 22 2025 10:45H to: OCTOBER 22 2025 20:45H)",
                dummyEvent.toString()); // Description with one word
        assertEquals("[E][ ] Dummy 2 (from: OCTOBER 22 2025 00:45H to: OCTOBER 22 2025 10:10H)",
                dummyTwoEvent.toString()); // Description with two words and date-time with leading zeros
        assertEquals("[E][ ] Dummy Event (from: OCTOBER 22 2025 10:00H to: OCTOBER 22 2025 20:00H)", 
                dummyTwoTwoEvent.toString()); // Description with two words and date-time with trailing zeros
    }

    @Test
    public void getTaskStringTest(){
        Event dummyEvent = new Event("Dummy", 
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER), 
                LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER));
        Event dummyTwoEvent = new Event("Dummy twp", 
                LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER), 
                LocalDateTime.parse("2025-10-22 10:10", DATE_TIME_FORMATTER));
        Event dummyTwoTwoEvent = new Event("Dummy two two", 
                LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER), 
                LocalDateTime.parse("2025-10-22 20:00", DATE_TIME_FORMATTER));

        assertEquals("E |   | Dummy | 2025-10-22 10:45 | 2025-10-22 20:45",
                dummyEvent.getTaskString()); // Description with one word
        assertEquals("E |   | Dummy 2 | 2025-10-22 00:45 | 2025-10-22 10:10",
                dummyTwoEvent.getTaskString()); // Description with two words and date-time with leading zeros
        assertEquals("E |   | Dummy Event | 2025-10-22 10:00 | 2025-10-22 20:00",
                dummyTwoTwoEvent.getTaskString()); // Description with two words and date-time with trailing zeros
    }

    @Test
    public void markTest() {
        Event dummyEvent = new Event("Dummy",
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER));

        assertEquals(false, dummyEvent.getIsDone()); // Initial state of Event
        dummyEvent.markAsDone();
        assertEquals(true, dummyEvent.getIsDone()); // Marking unmarked Event
    }

    @Test
    public void unmarkTest() {
        Event dummyEvent = new Event("Dummy",
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER),
                LocalDateTime.parse("2025-10-22 20:45", DATE_TIME_FORMATTER));

        dummyEvent.markAsDone();
        assertEquals(true, dummyEvent.getIsDone()); // Marking unmarked Event
        dummyEvent.markAsNotDone();
        assertEquals(false, dummyEvent.getIsDone()); // Unmarking marked Event
    }

}

