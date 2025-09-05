package toodoo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline task that can be added to the task list.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String decscription, LocalDateTime deadline) {
        super(decscription);

        assert deadline != null : "Deadline should not be null";

        this.deadline = deadline;
    }

    /**
     * Returns the string representation of a Deadline.
     * 
     * @return The type, status, description and deadline of a Deadline.
     */
    @Override
    public String toString() {
        assert getDescription() != null : "Description should not be null";

        return "[D]" + super.toString() + " (by: " + deadline.getMonth() + " " + deadline.getDayOfMonth() + " " 
                + deadline.getYear() + " " + String.format("%02d:%02dH", deadline.getHour(), deadline.getMinute()) + ")";
    }

    /**
     * Returns the string representation of a Deadline to be saved in the storage.
     * 
     * @return The type status, description and deadline of a Deadline.
     */
    @Override
    public String getTaskString() {
        assert getDescription() != null : "Description should not be null";
        
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + deadline.format(DATE_TIME_FORMATTER);
    }
}