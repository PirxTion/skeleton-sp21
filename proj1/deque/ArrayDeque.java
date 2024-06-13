package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 1;
    }

    public void addFirst(T item) {
        ifExpand();
        size++;
        items[head] = item;
        head = (head + items.length - 1) % items.length;
    }

    public void addLast(T item) {
        ifExpand();
        size++;
        items[tail] = item;
        tail = (tail + items.length + 1) % items.length;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        if (tail > head) {
            System.arraycopy(items, head, newItems, 0, tail + 1 - head);
        } else {
            System.arraycopy(items, head, newItems, 0, items.length - head);
            System.arraycopy(items, 0, newItems, items.length - head, tail + 1);
        }

        head = 0;
        tail = size + 1;
        items = newItems;

    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        head = (head + items.length + 1) % items.length;
        T item = items[head];
        items[head] = null;
        ifShrink();
        return item;
    }

    private void ifShrink() {
        if (items.length >= 16 && ((double) items.length / size) >= 4) {
            resize(size + 2);
        }
    }

    private void ifExpand() {
        if (items.length == size + 1) {
            resize(items.length * 2);
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        tail = (tail + items.length - 1) % items.length;
        T item = items[tail];
        items[tail] = null;
        ifShrink();
        return item;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        index = (head + index + 1) % items.length;
        return items[index];
    }

    public void printDeque() {
        int n = (head + 1) % items.length;
        for (int i = 0; i < size; i++) {
            System.out.print(items[n] + " ");
            n = (n + 1) % items.length;
        }
        System.out.println();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int i = (head + 1) % items.length;
        int count = 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public T next() {
            T item = items[i];
            i = (i + 1) % items.length;
            count++;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<T> q = (ArrayDeque<T>) o;
        if (q.size != this.size) {
            return false;
        }
        Iterator<T> qIterator = q.iterator();
        Iterator<T> iterator = this.iterator();

        while (qIterator.hasNext() && iterator.hasNext()) {
            if (!qIterator.next().equals(iterator.next())) {
                return false;
            }
        }
        return true;
    }
}
