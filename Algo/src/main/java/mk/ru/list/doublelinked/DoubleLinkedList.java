package mk.ru.list.doublelinked;

public class DoubleLinkedList {
    private DoubleLinkedListNode head; // Заголовок списка

    // Метод для добавления элемента в начало списка
    public void addAtBeginning(int data) {
        DoubleLinkedListNode newDoubleLinkedListNode = new DoubleLinkedListNode(data);
        if (head == null) {
            head = newDoubleLinkedListNode;
        } else {
            newDoubleLinkedListNode.next = head;
            head.prev = newDoubleLinkedListNode;
            head = newDoubleLinkedListNode;
        }
    }

    // Метод для добавления элемента в конец списка
    public void addAtEnd(int data) {
        DoubleLinkedListNode newDoubleLinkedListNode = new DoubleLinkedListNode(data);
        if (head == null) {
            head = newDoubleLinkedListNode;
        } else {
            DoubleLinkedListNode last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newDoubleLinkedListNode;
            newDoubleLinkedListNode.prev = last;
        }
    }

    // Метод для печати элементов списка
    public void printList() {
        DoubleLinkedListNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void removeByData(int data) {
        DoubleLinkedListNode current = head;
        while (current != null) {
            if (current.data == data) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else { // Если удаляемый узел - это голова списка
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else { // Если удаляемый узел - последний узел списка
                    current = current.prev;
                    current.next = null;

                }
                break;
            }
            current = current.next;
        }
    }

    public void removeByIndex(int index) {
        if (index < 0 || index >= size()) return; // Проверка на корректность индекса
        DoubleLinkedListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        } else { // Если удаляемый узел - это голова списка
            head = current.next;
            if (head != null) {
                head.prev = null;
            }
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else { // Если удаляемый узел - последний узел списка
            current = current.prev;
            current.next = null;
        }
    }

    public int getByIndex(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index out of bounds");
        DoubleLinkedListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Метод для получения размера списка
    public int size() {
        int count = 0;
        DoubleLinkedListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
