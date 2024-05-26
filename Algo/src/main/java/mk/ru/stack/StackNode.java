package mk.ru.stack;

public class StackNode<T> {
    T data; // Данные, хранящиеся в узле
    StackNode<T> next; // Ссылка на следующий узел в списке

    // Конструктор для создания нового узла
    public StackNode(T data) {
        this.data = data;
        this.next = null;
    }
}
