package toodoo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getMonth() + " " + from.getDayOfMonth() + " " 
                + from.getYear() + " " + String.format("%02d:%02dH", from.getHour(), from.getMinute()) 
                + " to: " + to.getMonth() + " " + to.getDayOfMonth() + " " + to.getYear() + " " 
                + String.format("%02d:%02dH", to.getHour(), to.getMinute()) + ")";
    }

    @Override
    public String getTaskString() {
        return "E | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + from.format(DATE_TIME_FORMATTER) 
                + " | " + to.format(DATE_TIME_FORMATTER);
    }
}
