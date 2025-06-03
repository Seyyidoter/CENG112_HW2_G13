package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericHistory<T> - A generic class to store and display a history of resolved items.
 * Maintains both insertion order and resolved order.
 */
public class GenericHistory<T> {
    private List<T> historyList;
    private long resolveCounter;

    public GenericHistory() {
        historyList = new ArrayList<>();
        resolveCounter = 1;
    }

    // Adds a resolved item to the history with resolve timestamp
    public void add(T item) {
        if (item instanceof Ticket) {
            // Set resolve time for proper DESC sorting
            ((Ticket) item).setResolveTime(resolveCounter++);
        }
        historyList.add(item);
    }

    /**
     * Returns a copy of all items in the history
     */
    public List<T> getAll() {
        return new ArrayList<>(historyList);
    }

    /**
     * Displays all items in the history with numbered list format
     * Useful for debugging and simple console output
     */
    public void display() {
        int count = 1;
        for (T item : historyList) {
            System.out.println(count++ + ". " + item.toString());
        }
    }
}
