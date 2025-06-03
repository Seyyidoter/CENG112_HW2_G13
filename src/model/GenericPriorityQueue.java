package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericPriorityQueue<T> - A custom generic priority queue implementation using
 * three separate internal queues for High, Medium, and Low priorities.
 */
public class GenericPriorityQueue<T extends Comparable<T>> {

    // Three separate queues for different priority levels
    private GenericQueue<T> highPriorityQueue;
    private GenericQueue<T> mediumPriorityQueue;
    private GenericQueue<T> lowPriorityQueue;

    /**
     * Constructor - Initializes all three priority queues as empty
     */
    public GenericPriorityQueue() {
        highPriorityQueue = new GenericQueue<>();
        mediumPriorityQueue = new GenericQueue<>();
        lowPriorityQueue = new GenericQueue<>();
    }

    // Adds an item to the appropriate priority queue
    public void offer(T item) {
        // Type safety check - currently only supports Ticket objects
        if (!(item instanceof Ticket)) {
            throw new IllegalArgumentException("Item must be a Ticket");
        }

        // Cast to Ticket and determine priority level
        Ticket ticket = (Ticket) item;
        String priority = ticket.getPriority().toLowerCase();

        // Add to appropriate queue based on priority
        switch (priority) {
            case "high":
                highPriorityQueue.enqueue(item);
                break;
            case "medium":
                mediumPriorityQueue.enqueue(item);
                break;
            case "low":
                lowPriorityQueue.enqueue(item);
                break;
            default:
                throw new IllegalArgumentException("Unknown priority: " + priority);
        }
    }

    /**
     * Retrieves and removes the highest priority item from the queue
     * Priority order: High > Medium > Low
     * Within same priority: FIFO (First In, First Out)
     */
    public T poll() {
        // Check high priority queue first
        if (!highPriorityQueue.isEmpty()) {
            return highPriorityQueue.dequeue();
        }
        // Then medium priority queue
        else if (!mediumPriorityQueue.isEmpty()) {
            return mediumPriorityQueue.dequeue();
        }
        // Finally low priority queue
        else if (!lowPriorityQueue.isEmpty()) {
            return lowPriorityQueue.dequeue();
        }
        // All queues are empty
        return null;
    }

    /**
     * Checks if all priority queues are empty
     */
    public boolean isEmpty() {
        return highPriorityQueue.isEmpty() &&
                mediumPriorityQueue.isEmpty() &&
                lowPriorityQueue.isEmpty();
    }

    // Returns all queued items in priority order: High -> Medium -> Low
    public List<T> getAll() {
        List<T> all = new ArrayList<>();
        all.addAll(highPriorityQueue.getAll());
        all.addAll(mediumPriorityQueue.getAll());
        all.addAll(lowPriorityQueue.getAll());
        return all;
    }

    // Returns all items sorted by arrival time (for asc/desc display)
    public List<T> getAllInArrivalOrder() {
        List<T> all = new ArrayList<>();
        all.addAll(highPriorityQueue.getAll());
        all.addAll(mediumPriorityQueue.getAll());
        all.addAll(lowPriorityQueue.getAll());

        // Sort by arrival time if items are Tickets
        all.sort((t1, t2) -> {
            if (t1 instanceof Ticket && t2 instanceof Ticket) {
                return Long.compare(((Ticket)t1).getArrivalTime(), ((Ticket)t2).getArrivalTime());
            }
            return 0; // No sorting for non-Ticket items
        });

        return all;
    }

    /**
     * Displays all items in priority order with numbered list format
     * Useful for debugging and console output
     */
    public void display() {
        int count = 1;
        for (T item : getAll()) {
            System.out.println(count++ + ". " + item.toString());
        }
    }
}
