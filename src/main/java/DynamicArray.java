public class DynamicArray {
    private int[] elements;
    private int size;

    private long accesses;
    private long comparisons;
    private long movements;

    public DynamicArray() {
        elements = new int[10];
    }

    public void add(int value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        ensureCapacity();

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
            movements++;
        }

        elements[index] = value;
        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removed = elements[index];
        accesses++;

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
            movements++;
        }

        size--;
        return removed;
    }

    public int get(int index) {
        checkIndex(index);
        accesses++;
        return elements[index];
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            accesses++;
            comparisons++;

            if (elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public long getAccesses() {
        return accesses;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getMovements() {
        return movements;
    }

    public void resetMetrics() {
        accesses = 0;
        comparisons = 0;
        movements = 0;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }

        int[] bigger = new int[elements.length * 2];

        for (int i = 0; i < size; i++) {
            bigger[i] = elements[i];
            movements++;
        }

        elements = bigger;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
