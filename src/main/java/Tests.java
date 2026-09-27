import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Tests {
    public static void main(String[] args) {
        testDynamicArray();
        testLinkedList();
        testMinHeap();
        System.out.println("ALL TESTS PASSED");
    }

    private static void testDynamicArray() {
        DynamicArray actual = new DynamicArray();
        ArrayList<Integer> expected = new ArrayList<>();

        check(actual.size() == 0, "DynamicArray: empty");
        actual.add(5);
        expected.add(5);
        actual.add(5);
        expected.add(5);
        actual.add(0, 10);
        expected.add(0, 10);
        actual.add(actual.size(), 20);
        expected.add(expected.size(), 20);

        for (int i = 0; i < 1000; i++) {
            actual.add(i);
            expected.add(i);
        }

        check(actual.contains(5), "DynamicArray: contains");
        check(!actual.contains(-999), "DynamicArray: absent value");
        check(actual.remove(0) == expected.remove(0),
                "DynamicArray: remove first");
        check(actual.remove(actual.size() - 1)
                        == expected.remove(expected.size() - 1),
                "DynamicArray: remove last");

        check(actual.size() == expected.size(), "DynamicArray: size");
        for (int i = 0; i < expected.size(); i++) {
            check(actual.get(i) == expected.get(i),
                    "DynamicArray: index " + i);
        }

        expectIndexError(() -> actual.get(-1));
        expectIndexError(() -> actual.get(actual.size()));
        expectIndexError(() -> actual.add(actual.size() + 1, 1));
        expectIndexError(() -> actual.remove(-1));

        System.out.println("DynamicArray PASSED");
    }

    private static void testLinkedList() {
        LinkedList actual = new LinkedList();
        java.util.LinkedList<Integer> expected = new java.util.LinkedList<>();

        check(actual.size() == 0, "LinkedList: empty");
        actual.add(5);
        expected.add(5);
        actual.add(5);
        expected.add(5);
        actual.add(0, 10);
        expected.add(0, 10);
        actual.add(actual.size(), 20);
        expected.add(expected.size(), 20);

        for (int i = 0; i < 1000; i++) {
            actual.add(i);
            expected.add(i);
        }

        check(actual.contains(5), "LinkedList: contains");
        check(!actual.contains(-999), "LinkedList: absent value");
        check(actual.remove(0) == expected.remove(0),
                "LinkedList: remove first");
        check(actual.remove(actual.size() - 1)
                        == expected.remove(expected.size() - 1),
                "LinkedList: remove last");

        check(actual.size() == expected.size(), "LinkedList: size");
        for (int i = 0; i < expected.size(); i++) {
            check(actual.get(i) == expected.get(i),
                    "LinkedList: index " + i);
        }

        expectIndexError(() -> actual.get(-1));
        expectIndexError(() -> actual.get(actual.size()));
        expectIndexError(() -> actual.add(actual.size() + 1, 1));
        expectIndexError(() -> actual.remove(-1));

        System.out.println("LinkedList PASSED");
    }

    private static void testMinHeap() {
        MinHeap actual = new MinHeap();
        PriorityQueue<Integer> expected = new PriorityQueue<>();

        check(actual.size() == 0, "MinHeap: empty");
        try {
            actual.peekMin();
            throw new AssertionError("MinHeap: empty peek should fail");
        } catch (NoSuchElementException ignored) {
            // Ожидаемое поведение
        }

        int[] values = {8, 3, 3, 10, -2, 7, 0};
        for (int value : values) {
            actual.insert(value);
            expected.add(value);
            check(actual.peekMin() == expected.peek(),
                    "MinHeap: minimum after insert");
        }

        for (int i = 1000; i >= 0; i--) {
            actual.insert(i);
            expected.add(i);
            check(actual.peekMin() == expected.peek(),
                    "MinHeap: minimum after large insert");
        }

        int previous = Integer.MIN_VALUE;
        while (!expected.isEmpty()) {
            int value = actual.extractMin();
            check(value == expected.remove(), "MinHeap: extractMin");
            check(value >= previous, "MinHeap: sorted extraction");
            previous = value;

            if (!expected.isEmpty()) {
                check(actual.peekMin() == expected.peek(),
                        "MinHeap: minimum after extract");
            }
        }

        check(actual.size() == 0, "MinHeap: empty after extraction");
        System.out.println("MinHeap PASSED");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void expectIndexError(Runnable operation) {
        try {
            operation.run();
            throw new AssertionError("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }
}
