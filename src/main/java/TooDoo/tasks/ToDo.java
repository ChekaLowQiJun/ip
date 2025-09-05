package toodoo.tasks;

/**
 * The ToDo task that can be added to the task list.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a ToDo.
     * 
     * @return The type, status, and description of a ToDo.
     */
    @Override
    public String toString() {
        assert getDescription() != null : "Description should not be null";
        
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of a ToDo to be saved in the storage.
     * 
     * @return The type, status and description of a ToDo.
     */
    @Override
    public String getTaskString() {
        assert getDescription() != null : "Description should not be null";

        return "T | " + this.getStatusIcon() + " | " + this.getDescription();
    }
}
