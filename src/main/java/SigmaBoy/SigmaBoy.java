package SigmaBoy;

import SigmaBoy.task.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SigmaBoy {
    //Constants
    private static final String LINE =
            "____________________________________________________________\n";

    private static final String HI =
            "Hello! I'm SigmaBoy\n" + "What can I do for you?";

    private static final String BYE =
            "Bye. Hope to see you again soon!";

    private static final int MAX_TASKS = 100;
    private static final int MARK_PREFIX = 4;
    private static final int UNMARK_PREFIX = 6;
    private static final int TODO_PREFIX = 4;
    private static final int DEADLINE_PREFIX = 8;
    private static final int EVENT_PREFIX = 5;
    private static final int DELETE_PREFIX = 6;

    private static final String EVENT_FORMAT_ERROR =
            "Wrong format! the correct format is event {description} /from {from} /to {to}";

    private static final String DEADLINE_FORMAT_ERROR =
            "Wrong format!, the correct format is deadline {description} /by {time}";

    private static final String INVALID_INDEX_ERROR =
            "Oi dont troll, choose an appropriate index";

    private static final String EMPTY_STATEMENT_ERROR =
            "Cannot have empty statements!";

    private static final String OUT_OF_RANGE_ERROR =
            "Out of range, choose another index";

    private static final String FILE_PATH = "data/SigmaBoy.txt";

    //Helper Functions
    private static void printLine(){
        System.out.println(LINE);
    }

    private static void printWithLine(String message) {
        System.out.println(message + "\n" + LINE);
    }

    private static void printAddedTask(Task task, int count) {
        printWithLine(
                "Got it. I've added this task:\n " + task +
                "\nNow you have " + count + " tasks in the list."
        );
    }

    private static void printDeleteTask(Task task, int count) {
        printWithLine(
                "Noted. Ive removed this task:\n" + task +
                "\nNow you have " + count + " tasks in the list."
        );
    }

    private static void printMarkStatus(Task task, boolean done) {
        if (done) {
            printWithLine("Nice! I've marked this task as done:\n" + task);
        } else {
            printWithLine("Ok! I've marked this task as not done yet:\n" + task);
        }
    }

    //Save task
    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task t : tasks) {
                String type = "";
                String extra = "";
                String done = t.isDone() ? "1" : "0";

                if (t instanceof Todo) {
                    type = "T";
                } else if (t instanceof Deadline) {
                    type = "D";
                    Deadline d = (Deadline) t;
                    extra = " | " + d.getBy();
                } else if (t instanceof Event) {
                    type = "E";
                    Event e = (Event) t;
                    extra = " | " + e.getFrom() + " | " + e.getTo();
                }

                writer.write(type + " | " + done + " | " + t.getDescription() + extra + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    //Load task
    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String desc = parts[2];

                if (type.equals("T")) {
                    Todo t = new Todo(desc);
                    if (isDone) t.markAsDone();
                    tasks.add(t);
                } else if (type.equals("D")) {
                    Deadline d = new Deadline(desc, parts[3]);
                    if (isDone) d.markAsDone();
                    tasks.add(d);
                } else if (type.equals("E")) {
                    Event e = new Event(desc, parts[3], parts[4]);
                    if (isDone) e.markAsDone();
                    tasks.add(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading: " + e.getMessage());
        }
        return tasks;
    }

    //Main
    public static void main(String[] args) {
        printLine();
        printWithLine(HI);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasks();

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();

            //Blank input
            if (userInput.isBlank()) {
                printWithLine("Oi dont troll");
                continue;
            }

            //Bye
            if (userInput.equals("bye")) {
                printWithLine(BYE);
                break;
            }

            //List
            if (userInput.equals("list")) {
                if (tasks.isEmpty()) {
                    printWithLine("No items in list yet");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    printLine();
                }
                continue;
            }

            //Mark and Unmark
            if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                boolean isMark = userInput.startsWith("mark");
                int prefixLength = isMark ? MARK_PREFIX : UNMARK_PREFIX;

                try {
                    int taskNum = Integer.parseInt(userInput.substring(prefixLength).trim());

                    if (taskNum < 1 || taskNum > tasks.size()) {
                        throw new SigmaBoyException(OUT_OF_RANGE_ERROR);
                    } else {
                        Task taskToMark = tasks.get(taskNum - 1);
                        if (isMark) {
                            taskToMark.markAsDone();
                        } else {
                            taskToMark.markAsNotDone();
                        }
                        saveTasks(tasks);
                        printMarkStatus(taskToMark, isMark);
                    }
                } catch(NumberFormatException e) {
                    printWithLine(INVALID_INDEX_ERROR);
                } catch (SigmaBoyException e) {
                    printWithLine(e.getMessage());
                }
                continue;
            }

            //Delete
            if (userInput.startsWith("delete")) {
                try {
                    int taskNum = Integer.parseInt(userInput.substring(DELETE_PREFIX).trim());

                    if (taskNum < 1 || taskNum > tasks.size()) {
                        throw new SigmaBoyException(OUT_OF_RANGE_ERROR);
                    } else {
                        Task removedTask = tasks.remove(taskNum - 1);
                        saveTasks(tasks);
                        printDeleteTask(removedTask, tasks.size());
                    }
                } catch (NumberFormatException e) {
                    printWithLine(INVALID_INDEX_ERROR);
                } catch (SigmaBoyException e) {
                    printWithLine(e.getMessage());
                }
                continue;
            }

            //Todo
            if (userInput.startsWith("todo")) {

                if (tasks.size() >= MAX_TASKS) {
                    printWithLine("Storage is full, terminating");
                    continue;
                }

                try {
                    String description = userInput.substring(TODO_PREFIX).trim();

                    if (description.isEmpty()) {
                        throw new SigmaBoyException(EMPTY_STATEMENT_ERROR);
                    }

                    Todo toDo = new Todo(description);
                    tasks.add(toDo);
                    saveTasks(tasks);
                    printAddedTask(toDo, tasks.size());

                } catch(SigmaBoyException e) {
                    printWithLine(e.getMessage());
                }
                continue;
            }

            //Deadline
            if (userInput.startsWith("deadline")) {

                if (tasks.size() >= MAX_TASKS) {
                    printWithLine("Storage is full, terminating");
                    continue;
                }

                try {
                    String rest = userInput.substring(DEADLINE_PREFIX).trim();
                    String[] parts = rest.split(" /by ");

                    if (parts.length != 2) {
                        throw new SigmaBoyException(DEADLINE_FORMAT_ERROR);
                    }

                    if (parts[0].isEmpty() || parts[1].isEmpty()) {
                        throw new SigmaBoyException(EMPTY_STATEMENT_ERROR);
                    }

                    Deadline deadline = new Deadline(parts[0].trim(), parts[1].trim());
                    tasks.add(deadline);
                    saveTasks(tasks);
                    printAddedTask(deadline, tasks.size());

                } catch (SigmaBoyException e) {
                    printWithLine(e.getMessage());
                }
                continue;
            }

            //Event
            if (userInput.startsWith("event")) {

                if (tasks.size() >= MAX_TASKS) {
                    printWithLine("Storage is full, terminating");
                    continue;
                }

                try {
                    String rest = userInput.substring(EVENT_PREFIX).trim();
                    String[] parts = rest.split(" /from | /to ");

                    if (parts.length != 3) {
                        throw new SigmaBoyException(EVENT_FORMAT_ERROR);
                    }

                    if (parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
                        throw new SigmaBoyException(EMPTY_STATEMENT_ERROR);
                    }

                    Event event = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(event);
                    saveTasks(tasks);
                    printAddedTask(event, tasks.size());
                    continue;

                } catch (SigmaBoyException e) {
                    printWithLine(e.getMessage());
                }
                continue;
            }

            String firstWord = userInput.split(" ")[0];
            printWithLine("Unknown command: " + firstWord);
        }

        scanner.close();
    }
}
