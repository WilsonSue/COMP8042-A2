package src.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NewListTest {

    @Test
    public void testRemoveEvens() throws Exception {
        NewList<Integer> list = new NewList<>(10);
        for (int i = 1; i <= 6; i++) {
            list.add(i); // Adds 1, 2, 3, 4, 5, 6
        }

        list.removeEvens(list);

        assertEquals(3, list.length(), "Length should be 3 after removing evens");
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void testRemoveOdds() throws Exception {
        NewList<Integer> list = new NewList<>(10);
        for (int i = 1; i <= 6; i++) {
            list.add(i); // Adds 1, 2, 3, 4, 5, 6
        }

        list.removeOdds(list);

        assertEquals(3, list.length(), "Length should be 3 after removing odds");
        assertEquals(2, list.get(0));
        assertEquals(4, list.get(1));
        assertEquals(6, list.get(2));
    }

    @Test
    public void testRemoveAllInstances() throws Exception {
        NewList<Integer> list = new NewList<>(10);
        int[] vals = {1, 5, 6, 4, 6, 2, 8, 6};
        for (int v : vals) {
            list.add(v);
        }

        list.removeAllInstances(list, 6);

        assertEquals(5, list.length(), "Length should be 5 after removing all 6s");
        assertEquals(1, list.get(0));
        assertEquals(5, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(2, list.get(3));
        assertEquals(8, list.get(4));
    }
}