import java.util.Scanner;

public class SigmaBoy {
    public static void main(String[] args) {
        final String LINE = "____________________________________________________________\n";
        final String HI = LINE + "Hello! I'm SigmaBoy\nWhat can I do for you?\n" + LINE;
        final String BYE= " Bye. Hope to see you again soon!\n";
        System.out.println(HI);

        Scanner scanner = new Scanner(System.in);
        String[] inputs = new String[100];
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
                                System.out.println(i + 1 + ". " + inputs[i]);
                            }
                        } else {

                            System.out.println("No items in list yet\n");
                        }
                    } else {
                        inputs[count] = userinput;
                        count++;
                        System.out.println("added: " + userinput);
                    }
                    System.out.println(LINE);
                }
            }
        }
        scanner.close();
    }
}
