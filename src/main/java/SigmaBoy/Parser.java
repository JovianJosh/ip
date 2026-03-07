package SigmaBoy;

/**
 * Parses user input commands for SigmaBoy task manager.
 * A <code>Parser</code> object corresponds to extracting command types
 * and arguments from raw input strings.
 */
public class Parser {
    // Command constants
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_FIND = "find";

    // Error messages
    private static final String EVENT_FORMAT_ERROR =
            "Wrong format! the correct format is event {description} /from {from} /to {to}";
    private static final String DEADLINE_FORMAT_ERROR =
            "Wrong format!, the correct format is deadline {description} /by {time}";
    private static final String INVALID_INDEX_ERROR =
            "Oi dont troll, choose an appropriate index";
    private static final String EMPTY_STATEMENT_ERROR =
            "Cannot have empty statements!";

    /**
     * Extracts the command word from user input.
     *
     * @param userInput The full user input string.
     * @return The first word of the input, which represents the command.
     */
    public static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Parses an index from commands that require a task number.
     *
     * @param userInput The full user input string.
     * @param command The command being executed (mark, unmark, delete).
     * @return The parsed index as an integer.
     * @throws SigmaBoyException If the index is invalid or missing.
     */
    public static int parseIndex(String userInput, String command) throws SigmaBoyException {
        try {
            int prefixLength = command.length() + 1; // +1 for space
            return Integer.parseInt(userInput.substring(prefixLength).trim());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new SigmaBoyException(INVALID_INDEX_ERROR);
        }
    }

    /**
     * Parses a todo command to extract the description.
     *
     * @param userInput The full user input string.
     * @return The task description.
     * @throws SigmaBoyException If the description is empty.
     */
    public static String parseTodo(String userInput) throws SigmaBoyException {
        String description = userInput.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new SigmaBoyException(EMPTY_STATEMENT_ERROR);
        }
        return description;
    }

    /**
     * Parses a deadline command to extract description and due date.
     *
     * @param userInput The full user input string.
     * @return A String array with [description, dueDate].
     * @throws SigmaBoyException If the format is invalid or parts are empty.
     */
    public static String[] parseDeadline(String userInput) throws SigmaBoyException {
        String rest = userInput.substring(COMMAND_DEADLINE.length()).trim();
        String[] parts = rest.split(" /by ");

        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new SigmaBoyException(DEADLINE_FORMAT_ERROR);
        }

        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    /**
     * Parses an event command to extract description, start time, and end time.
     *
     * @param userInput The full user input string.
     * @return A String array with [description, from, to].
     * @throws SigmaBoyException If the format is invalid or parts are empty.
     */
    public static String[] parseEvent(String userInput) throws SigmaBoyException {
        String rest = userInput.substring(COMMAND_EVENT.length()).trim();
        String[] parts = rest.split(" /from | /to ");

        if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
            throw new SigmaBoyException(EVENT_FORMAT_ERROR);
        }

        return new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()};
    }

    /**
     * Parses a find command to extract the search keyword.
     *
     * @param userInput The full user input string.
     * @return The search keyword.
     * @throws SigmaBoyException If the keyword is empty.
     */
    public static String parseFind(String userInput) throws SigmaBoyException {
        String keyword = userInput.substring(COMMAND_FIND.length()).trim();
        if (keyword.isEmpty()) {
            throw new SigmaBoyException(EMPTY_STATEMENT_ERROR);
        }
        return keyword;
    }
}