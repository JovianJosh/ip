package SigmaBoy;

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

    // Error messages
    private static final String EVENT_FORMAT_ERROR =
            "Wrong format! the correct format is event {description} /from {from} /to {to}";
    private static final String DEADLINE_FORMAT_ERROR =
            "Wrong format!, the correct format is deadline {description} /by {time}";
    private static final String INVALID_INDEX_ERROR =
            "Oi dont troll, choose an appropriate index";
    private static final String EMPTY_STATEMENT_ERROR =
            "Cannot have empty statements!";

    public static String getCommand(String userInput) {
        return userInput.split(" ")[0];
    }

    public static int parseIndex(String userInput, String command) throws SigmaBoyException {
        try {
            int prefixLength = command.length() + 1; // +1 for space
            return Integer.parseInt(userInput.substring(prefixLength).trim());
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new SigmaBoyException(INVALID_INDEX_ERROR);
        }
    }

    public static String parseTodo(String userInput) throws SigmaBoyException {
        String description = userInput.substring(COMMAND_TODO.length()).trim();
        if (description.isEmpty()) {
            throw new SigmaBoyException(EMPTY_STATEMENT_ERROR);
        }
        return description;
    }

    public static String[] parseDeadline(String userInput) throws SigmaBoyException {
        String rest = userInput.substring(COMMAND_DEADLINE.length()).trim();
        String[] parts = rest.split(" /by ");

        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            throw new SigmaBoyException(DEADLINE_FORMAT_ERROR);
        }

        return new String[]{parts[0].trim(), parts[1].trim()};
    }

    public static String[] parseEvent(String userInput) throws SigmaBoyException {
        String rest = userInput.substring(COMMAND_EVENT.length()).trim();
        String[] parts = rest.split(" /from | /to ");

        if (parts.length != 3 || parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
            throw new SigmaBoyException(EVENT_FORMAT_ERROR);
        }

        return new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()};
    }

}