package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        BuggyAList<Integer> buggyAList = new BuggyAList();
        AListNoResizing<Integer> aListNoResizing = new AListNoResizing<>();
        int[] items = new int[]{4,5,6};
        for(int i = 0; i < items.length; i++){
            buggyAList.addLast(items[i]);
            aListNoResizing.addLast(items[i]);
        }

        assertEquals(aListNoResizing.size(), buggyAList.size());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                buggyAList.addLast(randVal);
                L.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(), buggyAList.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0 || buggyAList.size() == 0) continue;
                assertEquals(L.getLast(), buggyAList.getLast());
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() == 0 || buggyAList.size() == 0) continue;
                assertEquals(L.removeLast(), buggyAList.removeLast());
            }
        }
    }
}
