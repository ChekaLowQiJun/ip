package TooDoo;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override 
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getTaskString() {
        return "Task | " + this.getStatusIcon() + " | " + this.getDescription();
    }
}