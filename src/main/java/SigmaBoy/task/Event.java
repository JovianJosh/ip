package SigmaBoy.task;

/**
 * Represents an event task in SigmaBoy task manager.
 * A <code>Event</code> object corresponds to a task with
 * a description, start time, and end time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event (String description, String from, String to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a String.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a String.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A formatted string with task type, status, description, and time range.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}