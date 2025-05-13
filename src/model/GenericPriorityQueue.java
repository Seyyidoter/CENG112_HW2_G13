package model;

public class GenericPriorityQueue<T extends Comparable<T>> {
    private GenericQueue<T> highPriorityQueue;
    private GenericQueue<T> mediumPriorityQueue;
    private GenericQueue<T> lowPriorityQueue;

    public GenericPriorityQueue() {
        highPriorityQueue = new GenericQueue<>();
        mediumPriorityQueue = new GenericQueue<>();
        lowPriorityQueue = new GenericQueue<>();
    }

}

