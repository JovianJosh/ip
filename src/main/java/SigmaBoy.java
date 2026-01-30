import java.util.Scanner;

public class SigmaBoy {
    public static void main(String[] args) {
        final String LINE = "____________________________________________________________\n";
        final String HI = LINE + "Hello! I'm SigmaBoy\nWhat can I do for you?\n" + LINE;
        final String BYE= " Bye. Hope to see you again soon!\n";
        System.out.println(HI);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String userinput = scanner.nextLine();

            System.out.println(LINE);
            if (userinput.equals("bye")) {
                System.out.println(BYE);
                System.out.println(LINE);
                break;
            } else {
                System.out.println(userinput);
                System.out.println(LINE);
            }
        }
        scanner.close();
    }
}
