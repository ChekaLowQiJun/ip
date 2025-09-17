package toodoo.tasks;

/**
 * A Task that has a description and a status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description The description of the Task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        assert !description.trim().isEmpty() : "Task description should not be empty";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return description;
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
        assert !isDone : "Task is not supposed to be already marked as done.";
        isDone = true;
    }

    /**
     * Unmarks the Task.
     */
    public void markAsNotDone() {
        assert isDone : "Task is supposed to be already marked as done.";
        isDone = false;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return The status and description of the Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of a Task to be saved in the storage.
     *
     * @return The type, status and description of a Task.
     */
    public String getTaskString() {
        assert getDescription() != null : "Description should not be null";

        return "Task | " + this.getStatusIcon() + " | " + this.getDescription();
    }

    /**
     * Returns the status of the Task.
     *
     * @return The status of the Task.
     */
    public boolean getIsDone() {
        return isDone;
    }
}
