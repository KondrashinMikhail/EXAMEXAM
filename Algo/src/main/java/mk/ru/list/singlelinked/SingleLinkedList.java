package mk.ru.list.singlelinked;

public class SingleLinkedList {
    SingleLinkedListNode head; // Голова списка

    // Метод для добавления элемента в начало списка
    public void addFirst(int data) {
        SingleLinkedListNode newSingleLinkedListNode = new SingleLinkedListNode(data);
        newSingleLinkedListNode.next = head;
        head = newSingleLinkedListNode;
    }

    // Метод для добавления элемента в конец списка
    public void addLast(int data) {
        SingleLinkedListNode newSingleLinkedListNode = new SingleLinkedListNode(data);
        if (head == null) {
            head = newSingleLinkedListNode;
        } else {
            SingleLinkedListNode last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newSingleLinkedListNode;
        }
    }

    // Метод для удаления первого элемента списка
    public void deleteFirst() {
        if (head == null) return;
        head = head.next;
    }

    // Метод для удаления последнего элемента списка
    public void deleteLast() {
        if (head == null) return;
        if (head.next == null) {
            head = null;
            return;
        }
        SingleLinkedListNode secondLast = head;
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }
        secondLast.next = null;
    }

    // Метод для вывода всех элементов списка
    public void display() {
        SingleLinkedListNode currentSingleLinkedListNode = head;
        while (currentSingleLinkedListNode != null) {
            System.out.print(currentSingleLinkedListNode.data + " -> ");
            currentSingleLinkedListNode = currentSingleLinkedListNode.next;
        }
        System.out.println("null"); // Завершаем строку вывода
    }
}
