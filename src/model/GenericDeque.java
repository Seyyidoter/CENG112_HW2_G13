package model;

import java.util.ArrayList;
import java.util.List;

public class GenericDeque<T> {
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

    public GenericDeque() {
        front = null;
        rear = null;
    }

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

    public T removeFront() {
        if (isEmpty()) return null;

        T data = front.data;
        front = front.next;
        if (front != null) front.prev = null;
        else rear = null;
        return data;
    }

    public T removeBack() {
        if (isEmpty()) return null;

        T data = rear.data;
        rear = rear.prev;
        if (rear != null) rear.next = null;
        else front = null;
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

