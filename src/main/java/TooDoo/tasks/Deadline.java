package toodoo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String decscription, LocalDateTime deadline) {
        super(decscription);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getMonth() + " " + deadline.getDayOfMonth() + " " 
                + deadline.getYear() + " " + String.format("%02d:%02dH", deadline.getHour(), deadline.getMinute()) + ")";
    }

    @Override
    public String getTaskString() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + deadline.format(DATE_TIME_FORMATTER);
    }
}