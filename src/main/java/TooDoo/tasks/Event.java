package TooDoo.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event task that can be added to the task list.
 */
public class Event extends Task {
    
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an Event.
     * 
     * @return The type, status, description, from and to of an Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.getMonth() + " " + this.from.getDayOfMonth() + " " + this.from.getYear() + " " + String.format("%02d:%02dH", this.from.getHour(), this.from.getMinute()) + " to: " + this.to.getMonth() + " " + this.to.getDayOfMonth() + " " + this.to.getYear() + " " + String.format("%02d:%02dH", this.to.getHour(), this.to.getMinute()) + ")";
    }

    /**
     * Returns the string representation of an Event to be saved in the storage.
     * 
     * @return The type, status, description, from and to of an Event.
     */
    @Override
    public String getTaskString() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.from.format(DATE_TIME_FORMATTER) + " | " + this.to.format(DATE_TIME_FORMATTER);
    }
}
