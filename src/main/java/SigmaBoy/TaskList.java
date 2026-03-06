package SigmaBoy;

import SigmaBoy.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final int MAX_TASKS = 100;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) throws SigmaBoyException {
        if (tasks.size() >= MAX_TASKS) {
            throw new SigmaBoyException("Storage is full, terminating");
        }
        tasks.add(task);
    }

    public Task remove(int index) throws SigmaBoyException {
        if (index < 0 || index >= tasks.size()) {
            throw new SigmaBoyException("Out of range, choose another index");
        }
        return tasks.remove(index);
    }

    public Task get(int index) throws SigmaBoyException {
        if (index < 0 || index >= tasks.size()) {
            throw new SigmaBoyException("Out of range, choose another index");
        }
        return tasks.get(index);
    }

    public void markAsDone(int index) throws SigmaBoyException {
        Task task = get(index);
        task.markAsDone();
    }

    public void markAsNotDone(int index) throws SigmaBoyException {
        Task task = get(index);
        task.markAsNotDone();
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}