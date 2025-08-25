package TooDoo.tasks;

/**
 * A Task that has a description and a status.
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     * 
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns an icon or space representing the status of the Task.
     * 
     * @return An X if the Task is done and a space otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); 
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the Task.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the Task.
     * 
     * @return The status and description of the Task.
     */
    @Override 
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of a Task to be saved in the storage.
     * 
     * @return The type, status and description of a Task.
     */
    public String getTaskString() {
        return "Task | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    /**
     * Returns the status of the Task.
     * 
     * @return The status of the Task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }
}