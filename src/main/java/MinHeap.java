import java.util.NoSuchElementException;

public class MinHeap {
    private int[] elements;
    private int size;
    private long comparisons;

    public MinHeap() {
        elements = new int[10];
        size = 0;
        comparisons = 0;
    }

    public void insert(int value) {
        ensureCapacity();

        int index = size;
        elements[index] = value;
        size++;

        while (index > 0) {
            int parent = (index - 1) / 2;
            comparisons++;

            if (elements[parent] <= elements[index]) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public int peekMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return elements[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        int minimum = elements[0];
        size--;

        if (size == 0) {
            return minimum;
        }

        elements[0] = elements[size];
        int index = 0;

        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size) {
                comparisons++;
                if (elements[left] < elements[smallest]) {
                    smallest = left;
                }
            }

            if (right < size) {
                comparisons++;
                if (elements[right] < elements[smallest]) {
                    smallest = right;
                }
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }

        return minimum;
    }

    public int size() {
        return size;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void resetComparisons() {
        comparisons = 0;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }

        int[] bigger = new int[elements.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = elements[i];
        }
        elements = bigger;
    }

    private void swap(int first, int second) {
        int temp = elements[first];
        elements[first] = elements[second];
        elements[second] = temp;
    }
}
