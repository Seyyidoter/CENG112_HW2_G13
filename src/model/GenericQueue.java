package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericQueue<T> - A generic FIFO (First-In-First-Out) queue implementation using a singly linked list.
 */

public class GenericQueue<T> {
    // Node class representing a single element in the queue.
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;

    // Constructs an empty queue.
    public GenericQueue() {
        front = null;
        rear = null;
    }

    // Adds an item to the end of the queue.
    public void enqueue(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Removes and returns the item from the front of the queue.
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null; // Queue is now empty
        }
        return data;
    }

    // Checks if the queue is empty.
    public boolean isEmpty() {
        return front == null;
    }

    // Displays all elements in the queue in order.
    public void display() {
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.data.toString());
            current = current.next;
        }
    }

    // Returns all elements in the queue as a list, in FIFO order.
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        Node current = front;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }
}
