package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    private int size;

    private Node sentinel;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node(null);
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node n;

        public LinkedListDequeIterator() {
            n = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return n.next != sentinel;
        }

        @Override
        public T next() {
            T item = n.item;
            n = n.next;
            return item;
        }
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
        if (!isEmpty()) {
            size--;
        }
        return n.item;
    }

    public T removeLast() {
        Node n = sentinel.prev;
        n.prev.next = sentinel;
        sentinel.prev = n.prev;
        if (!isEmpty()) {
            size--;
        }
        return n.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node n = sentinel.next;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return recersive(index, sentinel.next);
    }

    private T recersive(int index, Node n) {
        if (index == 0) {
            return n.item;
        } else {
            return recersive(index - 1, n.next);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<T> q = (LinkedListDeque<T>) o;
        if (q.size != this.size) {
            return false;
        }
         Iterator<T> qIterator = q.iterator();
         Iterator<T> iterator = this.iterator();

         while (qIterator.hasNext() && iterator.hasNext()){
             if (!qIterator.next().equals(iterator.next())) {
                 return false;
             }
         }
         return true;
    }
}
