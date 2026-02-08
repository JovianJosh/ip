import java.util.Scanner;

public class SigmaBoy {
    private static final String LINE =
            "____________________________________________________________\n";

    private static final String HI =
            LINE
                    + "Hello! I'm SigmaBoy\n"
                    +"What can I do for you?\n"
                    + LINE;

    private static final String BYE =
            " Bye. Hope to see you again soon!\n";

    private static final int MAX_TASKS = 100;
    private static final int MARK_PREFIX = 5;
    private static final int UNMARK_PREFIX = 7;
    private static final int TODO_PREFIX = 5;
    private static final int DEADLINE_PREFIX = 9;
    private static final int EVENT_PREFIX = 6;

    private static final String EVENT_FORMAT_ERROR =
            "Wrong format! the correct format is event {description} /from {from} /to {to}\n";

    private static final String DEADLINE_FORMAT_ERROR =
            "Wrong format!, the correct format is deadline {description} /by {time}\n";

    private static final String INVALID_INDEX_ERROR =
            "Oi dont troll, choose an appropriate index\n";

    private static final String EMPTY_STATEMENT_ERROR =
            "Cannot have empty statements!";

    public static void main(String[] args) {
        System.out.println(HI);
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int count = 0;

        while(scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.isBlank()) {
                System.out.println("Oi dont troll\n");
            } else {
                if (count >= MAX_TASKS) {
                    System.out.println("Storage is full, terminating\n");
                    break;
                } else {
                    System.out.println(LINE);
                    if (userInput.equals("bye")) {
                        System.out.println(BYE + LINE);
                        break;
                    } else if (userInput.equals("list")) {
                        if (count > 0) {
                            for (int i = 0; i < count; i++) {
                                System.out.println(i + 1 + ". " + tasks[i]);
                            }
                        } else {
                            System.out.println("No items in list yet\n");
                        }
                    } else if (userInput.startsWith("mark ")) {
                        try {
                            String num = userInput.substring(MARK_PREFIX).trim();
                            int taskNum = Integer.parseInt(num);

                            if (taskNum >= 1 && taskNum <= count) {
                                Task taskToMark = tasks[taskNum - 1];
                                taskToMark.markAsDone();
                                System.out.println("Nice! I've marked this task as done: \n" + taskToMark);
                            } else {
                                System.out.println("Out of range, choose another index\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(INVALID_INDEX_ERROR);
                        }
                    } else if (userInput.startsWith("unmark ")) {
                        try {
                            String num = userInput.substring(UNMARK_PREFIX).trim();
                            int taskNum = Integer.parseInt(num);

                            if (taskNum >= 1 && taskNum <= count) {
                                Task taskToMark = tasks[taskNum - 1];
                                taskToMark.markAsNotDone();
                                System.out.println("Ok! I've marked this task as not done yet: \n" + taskToMark);
                            } else {
                                System.out.println("Out of range, choose another index\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(INVALID_INDEX_ERROR);
                        }
                    } else if (userInput.startsWith("todo ")) {
                        String description = userInput.substring(TODO_PREFIX).trim();
                        Todo todo = new Todo(description);
                        if (!description.isEmpty()) {
                            tasks[count] = todo;
                            count++;
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + todo);
                            System.out.println("Now you have " + count + " tasks in the list.");
                        } else {
                            System.out.println(EMPTY_STATEMENT_ERROR);
                        }
                    } else if (userInput.startsWith("deadline ")) {
                        String rest = userInput.substring(DEADLINE_PREFIX).trim();
                        boolean isValid = true;

                        String[] parts = rest.split(" /by ");
                        if (parts.length != 2) {
                            System.out.println(DEADLINE_FORMAT_ERROR);
                            isValid = false;
                        }

                        if (isValid) {
                            if (!parts[0].isEmpty() && !parts[1].isEmpty()) {
                                String description = parts[0].trim();
                                String by = parts[1].trim();

                                Deadline deadline = new Deadline(description, by);
                                tasks[count] = deadline;
                                count++;
                                System.out.println("Got it. I've added this task:");
                                System.out.println(" " + deadline);
                                System.out.println("Now you have " + count + " tasks in the list.");
                            } else {
                                System.out.println(EMPTY_STATEMENT_ERROR);
                            }
                        }

                    } else if (userInput.startsWith("event ")) {
                        String rest = userInput.substring(EVENT_PREFIX).trim();
                        boolean isValid = true;

                        String[] parts = rest.split(" /from | /to ");
                        if (parts.length != 3){
                            System.out.println(EVENT_FORMAT_ERROR);
                            isValid = false;
                        }

                        if (isValid) {
                            if (!parts[2].isEmpty() && !parts[1].isEmpty() && !parts[0].isEmpty()) {
                                String description = parts[0].trim();
                                String from = parts[1].trim();
                                String to = parts[2].trim();

                                Event event = new Event(description, from, to);
                                tasks[count] = event;
                                count++;
                                System.out.println("Got it. I've added this task:");
                                System.out.println(" " + event);
                                System.out.println("Now you have " + count + " tasks in the list.");

                            } else {
                                System.out.println(EMPTY_STATEMENT_ERROR);
                            }
                        }
                    }

                    System.out.println(LINE);
                }
            }
        }
        scanner.close();
    }
}
