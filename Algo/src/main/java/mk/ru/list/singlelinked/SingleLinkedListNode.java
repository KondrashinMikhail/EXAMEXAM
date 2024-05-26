package mk.ru.list.singlelinked;

public class SingleLinkedListNode {
    int data; // Хранит данные
    SingleLinkedListNode next; // Ссылка на следующий узел

    // Конструктор для создания нового узла
    public SingleLinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}
