package model;

import java.util.ArrayList;
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
        return historyList;
    }

    public void display() {
        int count = 1;
        for (T item : historyList) {
            System.out.println(count++ + ". " + item.toString());
        }
    }
}

