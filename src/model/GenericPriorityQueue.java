package model;

import java.util.ArrayList;
import java.util.List;

public class GenericPriorityQueue<T extends Comparable<T>> {
    private GenericQueue<T> highPriorityQueue;
    private GenericQueue<T> mediumPriorityQueue;
    private GenericQueue<T> lowPriorityQueue;

    public GenericPriorityQueue() {
        highPriorityQueue = new GenericQueue<>();
        mediumPriorityQueue = new GenericQueue<>();
        lowPriorityQueue = new GenericQueue<>();
    }

    public void offer(T item) {
        // item instanceof Ticket kontrolü daha güvenli olurdu ama toString ile çözüm şu anlık yeterli:
        String s = item.toString().toLowerCase();
        if (s.contains("high")) {
            highPriorityQueue.enqueue(item);
        } else if (s.contains("medium")) {
            mediumPriorityQueue.enqueue(item);
        } else {
            lowPriorityQueue.enqueue(item);
        }
    }

    public T poll() {
        if (!highPriorityQueue.isEmpty()) {
            return highPriorityQueue.dequeue();
        } else if (!mediumPriorityQueue.isEmpty()) {
            return mediumPriorityQueue.dequeue();
        } else if (!lowPriorityQueue.isEmpty()) {
            return lowPriorityQueue.dequeue();
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return highPriorityQueue.isEmpty() &&
                mediumPriorityQueue.isEmpty() &&
                lowPriorityQueue.isEmpty();
    }

    public void displayByPriority() {
        List<T> all = getAll();
        int count = 1;
        for (T item : all) {
            System.out.println(count++ + ". " + item.toString());
        }
    }

    public List<T> getAll() {
        List<T> combined = new ArrayList<>();
        combined.addAll(highPriorityQueue.getAll());
        combined.addAll(mediumPriorityQueue.getAll());
        combined.addAll(lowPriorityQueue.getAll());
        return combined;
    }
}

