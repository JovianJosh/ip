package SigmaBoy;

import SigmaBoy.task.*;
import java.util.ArrayList;

public class SigmaBoy {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/SigmaBoy.txt";

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

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();

                // Blank input check
                if (userInput.isBlank()) {
                    ui.showError("Oi dont troll");
                    continue;
                }

                // Parse and get command from user's input
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

                    case Parser.COMMAND_EVENT:
                        handleEventCommand(userInput);
                        break;

                    case Parser.COMMAND_FIND:
                        handleFindCommand(userInput);
                        break;

                    default:
                        ui.showError("Unknown command: " + command);
                }
            } catch (SigmaBoyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

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

    private void handleDeleteCommand(String userInput) throws SigmaBoyException {
        int taskNum = Parser.parseIndex(userInput, Parser.COMMAND_DELETE);
        Task removedTask = tasks.remove(taskNum - 1);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removedTask, tasks.size());
    }

    private void handleTodoCommand(String userInput) throws SigmaBoyException {
        String description = Parser.parseTodo(userInput);
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(todo, tasks.size());
    }

    private void handleDeadlineCommand(String userInput) throws SigmaBoyException {
        String[] parts = Parser.parseDeadline(userInput);
        Deadline deadline = new Deadline(parts[0], parts[1]);
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(deadline, tasks.size());
    }

    private void handleEventCommand(String userInput) throws SigmaBoyException {
        String[] parts = Parser.parseEvent(userInput);
        Event event = new Event(parts[0], parts[1], parts[2]);
        tasks.add(event);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(event, tasks.size());
    }

    private void handleFindCommand(String userInput) throws SigmaBoyException {
        String keyword = Parser.parseFind(userInput);
        java.util.ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFindResults(matchingTasks, keyword);
    }

    public static void main(String[] args) {
        new SigmaBoy(FILE_PATH).run();
    }
}