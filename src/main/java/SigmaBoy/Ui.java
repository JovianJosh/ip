package SigmaBoy;

import SigmaBoy.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles user interface interactions for SigmaBoy task manager.
 * A <code>Ui</code> object corresponds to displaying messages,
 * reading user input, and formatting output.
 */
public class Ui {
    private static final String LINE =
            "____________________________________________________________\n";
    private Scanner scanner;

    /**
     * Constructs a Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a horizontal divider line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a message followed by a divider line.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println(message + "\n" + LINE);
    }

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm SigmaBoy\nWhat can I do for you?");
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message when tasks cannot be loaded from file.
     */
    public void showLoadingError() {
        showMessage("Error loading tasks from file.");
    }

    /**
     * Displays a generic error message.
     *
     * @param errorMessage The error message to display.
     */
    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }

    /**
     * Displays a confirmation message when a task is added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after addition.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        showMessage(
                "Got it. I've added this task:\n " + task +
                        "\nNow you have " + totalTasks + " tasks in the list."
        );
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks after deletion.
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        showMessage(
                "Noted. I've removed this task:\n" + task +
                        "\nNow you have " + totalTasks + " tasks in the list."
        );
    }

    /**
     * Displays the status change when a task is marked or unmarked.
     *
     * @param task The task whose status changed.
     * @param isMarked true if marked as done, false if marked as not done.
     */
    public void showMarkStatus(Task task, boolean isMarked) {
        if (isMarked) {
            showMessage("Nice! I've marked this task as done:\n" + task);
        } else {
            showMessage("Ok! I've marked this task as not done yet:\n" + task);
        }
    }

    /**
     * Displays the entire task list with numbered items.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No items in list yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            showLine();
        }
    }

    /**
     * Displays the results of a find command.
     *
     * @param matchingTasks The list of tasks that match the search keyword.
     * @param keyword The search keyword used.
     */
    public void showFindResults(ArrayList<Task> matchingTasks, String keyword) {
        if (matchingTasks.isEmpty()) {
            showMessage("No tasks found matching: " + keyword);
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
            showLine();
        }
    }

    /**
     * Reads a command from the user.
     *
     * @return The next line of user input, or empty string if none available.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }
}