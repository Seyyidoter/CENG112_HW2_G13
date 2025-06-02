package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericDeque<T> - A generic double-ended queue (deque) implementation using a doubly linked list.
 * Supports adding and removing elements from both the front and rear.
 */

public class GenericDeque<T> {
    // Inner class representing a node in the doubly linked list.
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;

    // Constructs an empty deque.
    public GenericDeque() {
        front = null;
        rear = null;
    }

    // Adds an item to the front of the deque.
    public void addFront(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
    }

    // Adds an item to the back of the deque.
    public void addBack(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
    }

    // Removes and returns the item from the front of the deque.
    public T removeFront() {
        if (isEmpty()) return null;

        T data = front.data;
        front = front.next;
        if (front != null) front.prev = null;
        else rear = null; // The deque is now empty
        return data;
    }

    //Removes and returns the item from the back of the deque.
    public T removeBack() {
        if (isEmpty()) return null;

        T data = rear.data;
        rear = rear.prev;
        if (rear != null) rear.next = null;
        else front = null; // The deque is now empty
        return data;
    }

    // Checks if the deque is empty.
    public boolean isEmpty() {
        return front == null;
    }

    // Displays all elements in the deque from front to rear.
    public void display() {
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.data.toString());
            current = current.next;
        }
    }

    // Returns all elements in the deque as a list.
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
