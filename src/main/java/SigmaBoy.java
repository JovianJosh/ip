import java.util.Scanner;

public class SigmaBoy {
    //Constants
    private static final String LINE =
            "____________________________________________________________\n";

    private static final String HI =
            "Hello! I'm SigmaBoy\n" + "What can I do for you?";

    private static final String BYE =
            "Bye. Hope to see you again soon!";

    private static final int MAX_TASKS = 100;
    private static final int MARK_PREFIX = 5;
    private static final int UNMARK_PREFIX = 7;
    private static final int TODO_PREFIX = 5;
    private static final int DEADLINE_PREFIX = 9;
    private static final int EVENT_PREFIX = 6;

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

    private static void printMarkStatus(Task task, boolean done) {
        if (done) {
            printWithLine("Nice! I've marked this task as done:\n" + task);
        } else {
            printWithLine("Ok! I've marked this task as not done yet:\n" + task);
        }
    }

    // ================= Main Program =================
    public static void main(String[] args) {
        printLine();
        printWithLine(HI);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int count = 0;

        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();

            if (userInput.isBlank()) {
                printWithLine("Oi dont troll");
                continue;
            }

            if (count >= MAX_TASKS) {
                printWithLine("Storage is full, terminating");
                break;
            }

            //Bye
            if (userInput.equals("bye")) {
                printWithLine(BYE);
                break;
            }

            //List
            if (userInput.equals("list")) {
                if (count == 0) {
                    printWithLine("No items in list yet");
                } else {
                    for (int i = 0; i < count; i++) {
                        printWithLine((i + 1) + ". " + tasks[i]);
                    }
                }
                continue;
            }

            //Mark and Unmark
            if (userInput.startsWith("mark ") || userInput.startsWith("unmark ")) {
                boolean isMark = userInput.startsWith("mark ");
                int prefixLength = isMark ? MARK_PREFIX : UNMARK_PREFIX;

                try {
                    int taskNum = Integer.parseInt(userInput.substring(prefixLength).trim());

                    if (taskNum < 1 || taskNum > count) {
                        printWithLine(OUT_OF_RANGE_ERROR);
                    } else {
                        Task taskToMark = tasks[taskNum - 1];
                        if (isMark) {
                            taskToMark.markAsDone();
                        } else {
                            taskToMark.markAsNotDone();
                        }
                        printMarkStatus(taskToMark, isMark);
                    }
                } catch (NumberFormatException e) {
                    printWithLine(INVALID_INDEX_ERROR);
                }
                continue;
            }

            //Todo
            if (userInput.startsWith("todo ")) {
                String description = userInput.substring(TODO_PREFIX).trim();
                if (description.isEmpty()) {
                    printWithLine(EMPTY_STATEMENT_ERROR);
                } else {
                    Todo todo = new Todo(description);
                    tasks[count++] = todo;
                    printAddedTask(todo, count);
                }
                continue;
            }

            //Deadline
            if (userInput.startsWith("deadline ")) {
                String rest = userInput.substring(DEADLINE_PREFIX).trim();
                String[] parts = rest.split(" /by ");

                if (parts.length != 2) {
                    printWithLine(DEADLINE_FORMAT_ERROR);
                    continue;
                }

                if (parts[0].isEmpty() || parts[1].isEmpty()) {
                    printWithLine(EMPTY_STATEMENT_ERROR);
                    continue;
                }

                Deadline deadline = new Deadline(parts[0].trim(), parts[1].trim());
                tasks[count++] = deadline;
                printAddedTask(deadline, count);
                continue;
            }

            //Event
            if (userInput.startsWith("event ")) {
                String rest = userInput.substring(EVENT_PREFIX).trim();
                String[] parts = rest.split(" /from | /to ");

                if (parts.length != 3) {
                    printWithLine(EVENT_FORMAT_ERROR);
                    continue;
                }

                if (parts[0].isEmpty() || parts[1].isEmpty() || parts[2].isEmpty()) {
                    printWithLine(EMPTY_STATEMENT_ERROR);
                    continue;
                }

                Event event = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks[count++] = event;
                printAddedTask(event, count);
            }
        }

        scanner.close();
    }
}
