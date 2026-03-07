package SigmaBoy.task;

/**
 * Represents a todo task in SigmaBoy task manager.
 * A <code>Todo</code> object corresponds to a simple task with
 * only a description and no additional date/time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return A formatted string with task type, status icon, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}