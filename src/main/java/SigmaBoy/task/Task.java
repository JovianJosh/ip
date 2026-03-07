package SigmaBoy.task;

/**
 * Represents an abstract task in SigmaBoy task manager.
 * A <code>Task</code> object corresponds to a generic task with
 * a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing task completion.
     *
     * @return "X" if task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(){
        isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if task is done, false otherwise.
     */
    public boolean isDone(){
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A formatted string with status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}