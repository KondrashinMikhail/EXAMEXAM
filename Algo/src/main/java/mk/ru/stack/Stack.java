package mk.ru.stack;

import java.util.*;

public class Stack<T> {
    private StackNode<T> top; // Вершина стека

    // Метод для добавления элемента в стек
    public void push(T element) {
        StackNode<T> newStackNode = new StackNode<>(element);
        newStackNode.next = top;
        top = newStackNode;
    }

    // Метод для удаления элемента с вершины стека
    public void pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T poppedElement = top.data;
        top = top.next;
    }

    // Метод для получения элемента с вершины стека без его удаления
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return top.data;
    }

    // Проверка, является ли стек пустым
    public boolean isEmpty() {
        return top == null;
    }

    // Очистка стека
    public void clear() {
        top = null;
    }
}
