package model;

import java.util.ArrayList;
import java.util.List;

public class GenericQueue<T> {
    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node front;
    private Node rear;

    public GenericQueue() {
        front = null;
        rear = null;
    }

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

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void display() {
        Node current = front;
        int count = 1;
        while (current != null) {
            System.out.println(count++ + ". " + current.data.toString());
            current = current.next;
        }
    }

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

