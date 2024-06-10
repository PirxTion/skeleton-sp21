package deque;

public class ArrayDeque<T> {
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
        //resize
        items[head] = item;
        head = (head + 7) % items.length;
        size++;
    }

    public void addLast(T item) {
        //resize
        items[tail] = item;
        tail = (tail + 9) % items.length;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        //resize
        head = (head + 9) % items.length;
        T item = items[head];
        items[head] = null;
        size--;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        //resize
        tail = (tail + 7) % items.length;
        T item = items[tail];
        items[tail] = null;
        size--;
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index >= size) return null;
        index = (head + index + 1) % 8;
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
}
