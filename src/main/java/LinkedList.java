public class LinkedList {
    private Node head;
    private int size;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public void add(int value) {
        add(size, value);
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node newNode = new Node(value);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node previous = nodeAt(index - 1);
            newNode.next = previous.next;
            previous.next = newNode;
        }

        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removed;

        if (index == 0) {
            removed = head.value;
            head = head.next;
        } else {
            Node previous = nodeAt(index - 1);
            removed = previous.next.value;
            previous.next = previous.next.next;
        }

        size--;
        return removed;
    }

    public int get(int index) {
        checkIndex(index);
        return nodeAt(index).value;
    }

    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    private Node nodeAt(int index) {
        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
