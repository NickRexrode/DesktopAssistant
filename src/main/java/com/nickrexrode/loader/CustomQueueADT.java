package com.nickrexrode.loader;

public class CustomQueueADT<T> {

    private Node head;
    private Node tail;
    private int size;
    private class Node {
        T data;
        Node next;
        Node prev;
    }


    public int size() {
        return size;
    }

    public void push(T data) {
        Node node = new Node();
        node.data = data;

        if (tail != null) {
            tail.next = node;
        }

        tail = node;

        if (head == null) {
            head = tail;
        }

        size++;
    }
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        Node node = head;
        this.head = this.head.next;

        return node.data;
    }

    public void empty() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size==0;
    }





}
