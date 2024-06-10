package deque;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest(){
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue(ad1.isEmpty());
        ad1.addFirst("front");
        assertEquals(1, ad1.size());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }
}
