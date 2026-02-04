import java.util.Scanner;

public class SigmaBoy {
    public static void main(String[] args) {
        final String LINE = "____________________________________________________________\n";
        final String HI = LINE + "Hello! I'm SigmaBoy\nWhat can I do for you?\n" + LINE;
        final String BYE= " Bye. Hope to see you again soon!\n";
        System.out.println(HI);

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0;

        while(true) {
            String userinput = scanner.nextLine();
            if (userinput.isBlank()) {
                System.out.println(LINE + "Oi dont troll\n" + LINE);
            } else {
                if (count >= 100) {
                    System.out.println("Storage is full, terminating\n");
                    break;
                } else {
                    System.out.println(LINE);
                    if (userinput.equals("bye")) {
                        System.out.println(BYE + LINE);
                        break;
                    } else if (userinput.equals("list")) {
                        if (count > 0) {
                            for (int i = 0; i < count; i++) {
                                System.out.println(i + 1 + ". " + tasks[i]);
                            }
                        } else {

                            System.out.println("No items in list yet\n");
                        }
                    } else if (userinput.startsWith("mark ")) {
                        try {
                            String num = userinput.substring(5).trim();
                            int taskNum = Integer.parseInt(num);

                            if (taskNum >= 1 && taskNum <= count) {
                                Task taskToMark = tasks[taskNum - 1];
                                taskToMark.markAsDone();
                                System.out.println("Nice! I've marked this task as done: \n" + taskToMark);
                            } else {
                                System.out.println("Out of range, choose another index\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Oi dont troll, choose an appropriate index\n");
                        }
                    } else if (userinput.startsWith("unmark ")) {
                        try {
                            String num = userinput.substring(7).trim();
                            int taskNum = Integer.parseInt(num);

                            if (taskNum >= 1 && taskNum <= count) {
                                Task taskToMark = tasks[taskNum - 1];
                                taskToMark.markAsNotDone();
                                System.out.println("Ok! I've marked this task as not done yet: \n" + taskToMark);
                            } else {
                                System.out.println("Out of range, choose another index\n");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Oi dont troll, choose an appropriate index\n");
                        }
                    } else if (userinput.startsWith("todo ")) {
                        String description = userinput.substring(5).trim();
                        Todo todo = new Todo(description);
                        if (!description.isEmpty()) {
                            tasks[count] = todo;
                            count++;
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + todo);
                            System.out.println("Now you have " + count + " tasks in the list.");
                        } else {
                            System.out.println("Please input a description");
                        }
                    } else if (userinput.startsWith("deadline ")) {
                        String rest = userinput.substring(9).trim();
                        boolean isValid = true;

                        String[] parts = rest.split(" /by ");
                        if (parts.length != 2){
                            System.out.println("Wrong format!, the correct format is deadline {description} /by {time}");
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
                                System.out.println("Cannot have empty statements!");
                            }
                        }

                    } else if (userinput.startsWith("event ")) {
                        String rest = userinput.substring(6).trim();
                        boolean isValid = true;

                        String[] parts = rest.split(" /from | /to ");
                        if (parts.length != 3){
                            System.out.println("Wrong format! the correct format is event {description} /from {from} /to {to}");
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
                                System.out.println("Cannot have empty statements!");
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
