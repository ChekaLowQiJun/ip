package toodoo.tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override 
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String getTaskString() {
        return "Task | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    public boolean getIsDone() {
        return isDone;
    }
}