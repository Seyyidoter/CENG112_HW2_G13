package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericHistory<T> - A generic class to store and display a history of resolved items (e.g., tickets).
 * Internally uses an ArrayList to maintain insertion order.
 */

public class GenericHistory<T> {
    private List<T> historyList;

    // Constructs an empty history list.
    public GenericHistory() {
        historyList = new ArrayList<>();
    }

    // Adds a resolved item to the history.
    public void add(T item) {
        historyList.add(item);
    }

    // Returns all items in the history.
    public List<T> getAll() {
        return historyList;
    }

    // Displays the history items in order of insertion.
    public void display() {
        int count = 1;
        for (T item : historyList) {
            System.out.println(count++ + ". " + item.toString());
        }
    }
}

