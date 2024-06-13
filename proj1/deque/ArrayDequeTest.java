package deque;

import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        assertTrue(ad1.isEmpty());
        ad1.addFirst(10);
        assertFalse(ad1.isEmpty());

        assertEquals(10, (double)ad1.removeFirst(), 0.0);
        assertTrue(ad1.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        assertEquals(0, (double) ad1.size(), 0.0);
    }

    @Test
    public void multipleParamTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<Boolean> ad2 = new ArrayDeque<>();
        ArrayDeque<Double> ad3 = new ArrayDeque<>();

        ad1.addFirst("string");
        ad3.addFirst(3.14159);
        ad2.addFirst(true);

        assertEquals("string", ad1.removeFirst());
        assertEquals(3.14159, ad3.removeFirst(), 0.00001);
        assertTrue(ad2.removeFirst());
    }

    @Test
    public void emptyNullReturnTest(){
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        assertNull(ad1.removeFirst());
        assertNull(ad1.removeLast());
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 0; i < 7; i++){
            ad1.addLast(i);
        }

        assertEquals(0, (double) ad1.get(0), 0.0);
        assertNull(ad1.get(8));

        assertEquals(4, (double) ad1.get(4), 0.0);
    }

    @Test
    public void resizeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        for (int i = 0; i < 7; i++){
            ad1.addLast(i);
        }

        ad1.addFirst(2);

        for (int i = 0; i < 16; i++){
            ad1.addFirst(i);
        }

        for (int i = 0; i < 24; i++){
            ad1.removeFirst();
        }

        ad1.removeFirst();

        for (int i = 0; i < 9; i++){
            ad1.addLast(i);
        }
        ad1.addFirst(1);
        ad1.addFirst(2);

        for (int i = 0; i < 8; i++){
            ad1.removeLast();
        }

    }


    @Test
    public void iterationTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }

        int j = 0;
        for (int i : lld1) {
            assertEquals(j, i, 0.0);
            j++;
        }
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        ArrayDeque<Integer> lld3 = new ArrayDeque<>();


        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
            lld2.addLast(i);
            lld3.addFirst(i);
        }

        assertNotEquals(lld1, lld3);
        assertEquals(lld1, lld2);
    }

}
