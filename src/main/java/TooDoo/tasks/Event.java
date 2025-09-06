package toodoo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event task that can be added to the task list.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event task.
     * @param description The description of the Event task.
     * @param from The starting time of the Event task.
     * @param to The ending time of the Event task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an Event.
     * @return The type, status, description, from and to of an Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getMonth() + " " + from.getDayOfMonth() + " "
                + from.getYear() + " " + String.format("%02d:%02dH", from.getHour(), from.getMinute())
                + " to: " + to.getMonth() + " " + to.getDayOfMonth() + " " + to.getYear() + " "
                + String.format("%02d:%02dH", to.getHour(), to.getMinute()) + ")";
    }

    /**
     * Returns the string representation of an Event to be saved in the storage.
     * @return The type, status, description, from and to of an Event.
     */
    @Override
    public String getTaskString() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + from.format(DATE_TIME_FORMATTER)
                + " | " + to.format(DATE_TIME_FORMATTER);
    }
}
