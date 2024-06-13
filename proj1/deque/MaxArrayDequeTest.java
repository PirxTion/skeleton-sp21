package deque;

import jh61b.junit.In;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MaxArrayDequeTest<T> {

    private class StringLengthComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }

    private class StringCharacterComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }


    @Test
    public void maxTest(){
        MaxArrayDeque<String> strings = new MaxArrayDeque<>(new StringLengthComparator());

        strings.addFirst("hello world");
        strings.addLast("hi");

        assertEquals("hello world", strings.max());
        assertEquals("hi", strings.max(new StringCharacterComparator()));
    }
}
