package toodoo.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void toStringTest() {
        Deadline dummyDeadline = new Deadline("Dummy",
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER));
        Deadline dummyTwoDeadline = new Deadline("Dummy two",
                LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER));
        Deadline dummyTwoTwoDeadline = new Deadline("Dummy two two",
                LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER));

        assertEquals("[D][ ] Dummy (by: OCTOBER 22 2025 10:45H)",
                dummyDeadline.toString()); // Description with one word
        assertEquals("[D][ ] Dummy two (by: OCTOBER 22 2025 00:45H)",
                dummyTwoDeadline.toString()); // Description with two words and date-time with leading zeros
        assertEquals("[D][ ] Dummy two two (by: OCTOBER 22 2025 10:00H)",
                dummyTwoTwoDeadline.toString()); // Description with two words and date-time with trailing zeros
    }

    @Test
    public void getTaskStringTest() {
        Deadline dummyDeadline = new Deadline("Dummy",
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER));
        Deadline dummyTwoDeadline = new Deadline("Dummy two",
                LocalDateTime.parse("2025-10-22 00:45", DATE_TIME_FORMATTER));
        Deadline dummyTwoTwoDeadline = new Deadline("Dummy two two",
                LocalDateTime.parse("2025-10-22 10:00", DATE_TIME_FORMATTER));

        assertEquals("D |   | Dummy | 2025-10-22 10:45",
                dummyDeadline.getTaskString());
        assertEquals("D |   | Dummy two | 2025-10-22 00:45",
                dummyTwoDeadline.getTaskString());
        assertEquals("D |   | Dummy two two | 2025-10-22 10:00",
                dummyTwoTwoDeadline.getTaskString());
    }

    @Test
    public void markTest() {
        Deadline dummyTask = new Deadline("Dummy",
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER));

        assertEquals(false, dummyTask.getIsDone()); // Initial state of Deadline
        dummyTask.markAsDone();
        assertEquals(true, dummyTask.getIsDone()); // Marking unmarked Deadline
    }

    @Test
    public void unmarkTest() {
        Deadline dummyTask = new Deadline("Dummy",
                LocalDateTime.parse("2025-10-22 10:45", DATE_TIME_FORMATTER));
        dummyTask.markAsDone();
        assertEquals(true, dummyTask.getIsDone()); // Marking unmarked Deadline
        dummyTask.markAsNotDone();
        assertEquals(false, dummyTask.getIsDone()); // Unmarking marked Deadline
    }

}
