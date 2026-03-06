package SigmaBoy;

import SigmaBoy.task.Task;
import java.util.Scanner;

public class Ui {
    private static final String LINE =
            "____________________________________________________________\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showMessage(String message) {
        System.out.println(message + "\n" + LINE);
    }

    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm SigmaBoy\nWhat can I do for you?");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        showMessage("Error loading tasks from file.");
    }

    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        showMessage(
                "Got it. I've added this task:\n " + task +
                        "\nNow you have " + totalTasks + " tasks in the list."
        );
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        showMessage(
                "Noted. I've removed this task:\n" + task +
                        "\nNow you have " + totalTasks + " tasks in the list."
        );
    }

    public void showMarkStatus(Task task, boolean isMarked) {
        if (isMarked) {
            showMessage("Nice! I've marked this task as done:\n" + task);
        } else {
            showMessage("Ok! I've marked this task as not done yet:\n" + task);
        }
    }

    public void showTaskList(java.util.ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No items in list yet");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            showLine();
        }
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }
}