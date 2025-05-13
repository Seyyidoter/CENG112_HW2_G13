package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GenericHistory<T> {
    private List<T> historyList;

    public GenericHistory() {
        historyList = new ArrayList<>();
    }

    public void add(T item) {
        historyList.add(item);
    }

    public List<T> getAll() {
        return new ArrayList<>(historyList); // kopya liste döner
    }

    public void display() {
        int count = 1;
        for (T item : historyList) {
            System.out.println(count++ + ". " + item.toString());
        }
    }

    public void displayAscending() {
        int count = 1;
        for (T item : historyList) {
            System.out.println(count++ + ". " + item.toString());
        }
    }

    public void displayDescending() {
        List<T> reversed = new ArrayList<>(historyList);
        Collections.reverse(reversed);
        int count = 1;
        for (T item : reversed) {
            System.out.println(count++ + ". " + item.toString());
        }
    }

    public void displaySortedByName(Comparator<T> comparator) {
        List<T> sorted = new ArrayList<>(historyList);
        sorted.sort(comparator);
        int count = 1;
        for (T item : sorted) {
            System.out.println(count++ + ". " + item.toString());
        }
    }
}
