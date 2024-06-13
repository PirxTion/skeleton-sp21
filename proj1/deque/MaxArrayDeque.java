package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
       this.comparator = c;
    }

    public T max(){
        return max(comparator);
    }

    public T max(Comparator<T> c){
        if (isEmpty()) {
            return null;
        }
        Iterator<T> iterator = iterator();
        T max = iterator.next();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (c.compare(max, item)< 0) {
                max = item;
            }
        }
        return max;

    }
}
