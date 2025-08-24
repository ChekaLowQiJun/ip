import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String decscription, LocalDateTime by) {
        super(decscription);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.getMonth() + " " + this.by.getDayOfMonth() + " " + this.by.getYear() + " " + String.format("%02d:%02dH", this.by.getHour(), this.by.getMinute()) + ")";
    }

    @Override
    public String getTaskString() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.by.format(DATE_TIME_FORMATTER);
    }
}