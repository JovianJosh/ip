package SigmaBoy;

import SigmaBoy.task.*;

/**
 * Represents the main application controller for SigmaBoy task manager.
 * A <code>SigmaBoy</code> object corresponds to the main program loop
 * that handles user commands and coordinates between UI, storage, and task list.
 */
public class SigmaBoy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/SigmaBoy.txt";

    /**
     * Constructs a SigmaBoy instance with the specified file path.
     * Initializes UI, storage, and loads tasks from the file if it exists.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public SigmaBoy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SigmaBoyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program loop until the user issues the "bye" command.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();

                if (userInput.isBlank()) {
                    ui.showError("Oi dont troll");
                    continue;
                }

                String command = Parser.getCommand(userInput);

                switch (command) {
                    case Parser.COMMAND_BYE:
                        ui.showGoodbye();
                        return;

                    case Parser.COMMAND_LIST:
                        ui.showTaskList(tasks.getTasks());
                        break;

                    case Parser.COMMAND_MARK:
                        handleMarkCommand(userInput, command);
                        break;

                    case Parser.COMMAND_UNMARK:
                        handleMarkCommand(userInput, command);
                        break;

                    case Parser.COMMAND_DELETE:
                        handleDeleteCommand(userInput);
                        break;

                    case Parser.COMMAND_TODO:
                        handleTodoCommand(userInput);
                        break;

                    case Parser.COMMAND_DEADLINE:
                        handleDeadlineCommand(userInput);
                        break;

                    case Parser.COMMAND_FIND:
                        handleFindCommand(userInput);
                        break;

                    case Parser.COMMAND_EVENT:
                        handleEventCommand(userInput);
                        break;

                    default:
                        ui.showError("Unknown command: " + command);
                }
            } catch (SigmaBoyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Handles the mark and unmark commands.
     *
     * @param userInput User input string.
     * @param command The command type (mark or unmark).
     * @throws SigmaBoyException If index is invalid.
     */
    private void handleMarkCommand(String userInput, String command) throws SigmaBoyException {
        boolean isMark = command.equals(Parser.COMMAND_MARK);
        int taskNum = Parser.parseIndex(userInput, command);

        if (isMark) {
            tasks.markAsDone(taskNum - 1);
        } else {
            tasks.markAsNotDone(taskNum - 1);
        }

        storage.save(tasks.getTasks());
        ui.showMarkStatus(tasks.get(taskNum - 1), isMark);
    }

    /**
     * Handles the delete command.
     *
     * @param userInput User input string.
     * @throws SigmaBoyException If index is invalid.
     */
    private void handleDeleteCommand(String userInput) throws SigmaBoyException {
        int taskNum = Parser.parseIndex(userInput, Parser.COMMAND_DELETE);
        Task removedTask = tasks.remove(taskNum - 1);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removedTask, tasks.size());
    }

    /**
     * Handles the todo command.
     *
     * @param userInput User input string.
     * @throws SigmaBoyException If description is empty.
     */
    private void handleTodoCommand(String userInput) throws SigmaBoyException {
        String description = Parser.parseTodo(userInput);
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(todo, tasks.size());
    }

    /**
     * Handles the deadline command.
     *
     * @param userInput User input string.
     * @throws SigmaBoyException If format is invalid.
     */
    private void handleDeadlineCommand(String userInput) throws SigmaBoyException {
        String[] parts = Parser.parseDeadline(userInput);
        Deadline deadline = new Deadline(parts[0], parts[1]);
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(deadline, tasks.size());
    }

    /**
     * Handles the event command.
     *
     * @param userInput User input string.
     * @throws SigmaBoyException If format is invalid.
     */
    private void handleEventCommand(String userInput) throws SigmaBoyException {
        String[] parts = Parser.parseEvent(userInput);
        Event event = new Event(parts[0], parts[1], parts[2]);
        tasks.add(event);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(event, tasks.size());
    }

    /**
     * Handles the find command.
     *
     * @param userInput User input string.
     * @throws SigmaBoyException If keyword is empty.
     */
    private void handleFindCommand(String userInput) throws SigmaBoyException {
        String keyword = Parser.parseFind(userInput);
        java.util.ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFindResults(matchingTasks, keyword);
    }

    /**
     * The entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new SigmaBoy(FILE_PATH).run();
    }
}