package SigmaBoy;

/**
 * Represents custom exceptions for SigmaBoy task manager.
 * A <code>SigmaBoyException</code> object corresponds to errors
 * that occur during task management operations.
 */
public class SigmaBoyException extends Exception {

    /**
     * Constructs a SigmaBoyException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public SigmaBoyException(String message) {
        super(message);
    }
}