package SigmaBoy;

import SigmaBoy.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks for SigmaBoy task manager.
 * A <code>TaskList</code> object corresponds to a collection of tasks
 * that can be added, removed, marked, and searched.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private static final int MAX_TASKS = 100;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @throws SigmaBoyException If the task list has reached maximum capacity.
     */
    public void add(Task task) throws SigmaBoyException {
        if (tasks.size() >= MAX_TASKS) {
            throw new SigmaBoyException("Storage is full, terminating");
        }
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove (0-based).
     * @return The removed task.
     * @throws SigmaBoyException If the index is out of range.
     */
    public Task remove(int index) throws SigmaBoyException {
        if (index < 0 || index >= tasks.size()) {
            throw new SigmaBoyException("Out of range, choose another index");
        }
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     * @throws SigmaBoyException If the index is out of range.
     */
    public Task get(int index) throws SigmaBoyException {
        if (index < 0 || index >= tasks.size()) {
            throw new SigmaBoyException("Out of range, choose another index");
        }
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark (0-based).
     * @throws SigmaBoyException If the index is out of range.
     */
    public void markAsDone(int index) throws SigmaBoyException {
        Task task = get(index);
        task.markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to unmark (0-based).
     * @throws SigmaBoyException If the index is out of range.
     */
    public void markAsNotDone(int index) throws SigmaBoyException {
        Task task = get(index);
        task.markAsNotDone();
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The search term to look for in task descriptions.
     * @return An ArrayList of tasks that contain the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}