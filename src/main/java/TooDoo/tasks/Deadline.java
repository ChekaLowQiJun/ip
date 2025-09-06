package toodoo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline task that can be added to the task list.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime deadline;

    /**
     * Constructs a Deadline task.
     * @param decscription The description of the Deadline task.
     * @param deadline The deadline of the Deadline task.
     */
    public Deadline(String decscription, LocalDateTime deadline) {
        super(decscription);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of a Deadline.
     * @return The type, status, description and deadline of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getMonth() + " " + deadline.getDayOfMonth() + " "
                + deadline.getYear() + " "
                + String.format("%02d:%02dH", deadline.getHour(), deadline.getMinute()) + ")";
    }

    /**
     * Returns the string representation of a Deadline to be saved in the storage.
     * @return The type status, description and deadline of a Deadline.
     */
    @Override
    public String getTaskString() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | "
                + deadline.format(DATE_TIME_FORMATTER);
    }
}
