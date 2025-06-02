package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericPriorityQueue<T> - A custom generic priority queue implementation using
 * three separate internal queues for High, Medium, and Low priorities.
 *
 * This class assumes that the type T is a Ticket or similar object that includes
 * a getPriority() method returning "High", "Medium", or "Low".
 */

public class GenericPriorityQueue<T extends Comparable<T>> {

    private GenericQueue<T> highPriorityQueue;
    private GenericQueue<T> mediumPriorityQueue;
    private GenericQueue<T> lowPriorityQueue;

    // Constructs an empty GenericPriorityQueue with three internal queues.
    public GenericPriorityQueue() {
        highPriorityQueue = new GenericQueue<>();
        mediumPriorityQueue = new GenericQueue<>();
        lowPriorityQueue = new GenericQueue<>();
    }

    // Adds an item to the appropriate priority queue.
    public void offer(T item) {
        int priorityValue = extractPriorityValue(item);
        switch (priorityValue) {
            case 3:
                highPriorityQueue.enqueue(item);
                break;
            case 2:
                mediumPriorityQueue.enqueue(item);
                break;
            case 1:
                lowPriorityQueue.enqueue(item);
                break;
            default:
                throw new IllegalArgumentException("Unknown priority for item: " + item.toString());
        }
    }

    /**
     * Retrieves and removes the next item based on priority:
     * High > Medium > Low. Within the same level, FIFO order is preserved.
     */
    public T poll() {
        if (!highPriorityQueue.isEmpty()) {
            return highPriorityQueue.dequeue();
        } else if (!mediumPriorityQueue.isEmpty()) {
            return mediumPriorityQueue.dequeue();
        } else if (!lowPriorityQueue.isEmpty()) {
            return lowPriorityQueue.dequeue();
        }
        return null;
    }

    // Checks whether all internal queues are empty.
    public boolean isEmpty() {
        return highPriorityQueue.isEmpty() && mediumPriorityQueue.isEmpty() && lowPriorityQueue.isEmpty();
    }

    // Returns all queued items in the order: High -> Medium -> Low.
    public List<T> getAll() {
        List<T> all = new ArrayList<>();
        all.addAll(highPriorityQueue.getAll());
        all.addAll(mediumPriorityQueue.getAll());
        all.addAll(lowPriorityQueue.getAll());
        return all;
    }

    // Displays all items in the queue in priority order.
    public void display() {
        int count = 1;
        for (T item : getAll()) {
            System.out.println(count++ + ". " + item.toString());
        }
    }

    /**
     * Helper method to extract priority as an integer value.
     * Assumes T is a Ticket; returns 3 for High, 2 for Medium, 1 for Low.
     */
    private int extractPriorityValue(T item) {
        if (item instanceof Ticket) {
            String priority = ((Ticket) item).getPriority().toLowerCase();
            switch (priority) {
                case "high":
                    return 3;
                case "medium":
                    return 2;
                case "low":
                    return 1;
                default:
                    return 0;
            }
        }
        return 0;
    }
}
