package SigmaBoy.task;

/**
 * Represents a deadline task in SigmaBoy task manager.
 * A <code>Deadline</code> object corresponds to a task with
 * a description and a due date/time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date/time of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date/time of the deadline task.
     *
     * @return The due date/time as a String.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A formatted string with task type, status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}