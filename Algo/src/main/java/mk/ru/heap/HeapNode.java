package mk.ru.heap;

public class HeapNode {
    private int value;

    public HeapNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
