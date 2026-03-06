package SigmaBoy;

import SigmaBoy.task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws SigmaBoyException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

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
            throw new SigmaBoyException("Error loading: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws SigmaBoyException {
        try {
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer = new FileWriter(filePath);

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
            throw new SigmaBoyException("Error saving: " + e.getMessage());
        }
    }
}