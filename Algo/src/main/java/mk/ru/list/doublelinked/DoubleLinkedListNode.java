package mk.ru.list.doublelinked;

public class DoubleLinkedListNode {
    public int data;
    public DoubleLinkedListNode next;
    DoubleLinkedListNode prev;

    public DoubleLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
