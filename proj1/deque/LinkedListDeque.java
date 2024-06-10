package deque;

import java.rmi.server.UnicastRemoteObject;

public class LinkedListDeque<T> {

    private int size;

    private Node sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node(null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }


    public class Node {
        Node prev;
        Node next;
        T item;

        public Node(T item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    public void addFirst(T item) {
        Node n = new Node(item);

        n.next = sentinel.next;
        sentinel.next.prev = n;
        n.prev = sentinel;
        sentinel.next = n;
        size++;
    }

    public void addLast(T item) {
        Node n = new Node(item);

        n.prev = sentinel.prev;
        sentinel.prev.next = n;
        n.next = sentinel;
        sentinel.prev = n;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node n = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(n.item + " ");
            n = n.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        Node n = sentinel.next;
        n.next.prev = sentinel;
        sentinel.next = n.next;
        if (!isEmpty()) size--;
        return n.item;
    }

    public T removeLast() {
        Node n = sentinel.prev;
        n.prev.next = sentinel;
        sentinel.prev = n.prev;
        if (!isEmpty()) size--;
        return n.item;
    }

    public T get(int index) {
        if (index >= size) return null;
        Node n = sentinel.next;
        for (int i = 0; i < index; i++){
            n = n.next;
        }
        return n.item;
    }

    public T getRecursive(int index) {
        if(index >= size) return null;
        return recersive(index, sentinel.next);
    }

    private T recersive(int index, Node n){
        if (index == 0) {
            return n.item;
        } else {
            return recersive(index - 1, n.next);
        }
    }

}
