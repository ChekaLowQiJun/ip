public class Deadline extends Task {

    private String by;

    public Deadline(String decscription, String by) {
        super(decscription);
        this.by = by;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getTaskString() {
        return "D | " + this.getStatusIcon() + " | " + this.getDescription() + " | " + this.by;
    }
}