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

    public boolean isEmpty() {
        return highPriorityQueue.isEmpty() && mediumPriorityQueue.isEmpty() && lowPriorityQueue.isEmpty();
    }

    public List<T> getAll() {
        List<T> all = new ArrayList<>();
        all.addAll(highPriorityQueue.getAll());
        all.addAll(mediumPriorityQueue.getAll());
        all.addAll(lowPr


