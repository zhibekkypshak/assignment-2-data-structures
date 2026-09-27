public class DynamicArray {
    private int[] elements;
    private int size;

    public DynamicArray() {
        elements = new int[10];
        size = 0;
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
        }

        elements[index] = value;
        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removed = elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        return removed;
    }

    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == value) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
