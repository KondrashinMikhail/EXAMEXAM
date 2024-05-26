package mk.ru;

import mk.ru.heap.*;
import mk.ru.list.doublelinked.*;
import mk.ru.list.singlelinked.*;
import mk.ru.stack.*;
import mk.ru.tree.*;

public class Test {

    public void binaryTreeTest() {
        BinaryTree tree = new BinaryTree();
        tree.addNode(50);
        tree.addNode(30);
        tree.addNode(20);
        tree.addNode(40);
        tree.addNode(70);
        tree.addNode(60);
        tree.addNode(80);
        System.out.println("Level Order Traversal:");
        tree.printLevelOrder();
        System.out.println("\nIs 60 present? " + tree.findNode(60));
        System.out.println("Is 100 present? " + tree.findNode(100));
        tree.removeNode(30);
        System.out.println("Level Order Traversal after deletion:");
        System.out.println("Is 30 present? " + tree.findNode(30));
        tree.printLevelOrder();
    }

    public void binaryHeapTest() {
        BinaryHeap heap = new BinaryHeap(31);
        heap.insertNode(120);
        heap.insertNode(40);
        heap.insertNode(50);
        heap.insertNode(80);
        heap.insertNode(20);
        heap.insertNode(100);
        heap.insertNode(150);
        heap.insertNode(30);
        heap.insertNode(210);
        heap.insertNode(180);
        heap.insertNode(10);
        heap.insertNode(90);
        heap.printHeap();

        heap.removeNode(1);
        heap.printHeap();

        heap.changeNode(0, 15);
        heap.printHeap();
    }

    public void singleLinkedListTest() {
        SingleLinkedList list = new SingleLinkedList();
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addFirst(40);
        list.addFirst(50);
        list.display();
        list.deleteFirst();
        list.display();
        list.deleteLast();
        list.display();
    }

    public void doubleLinkedListTest() {
        DoubleLinkedList dll = new DoubleLinkedList();
        dll.addAtBeginning(10);
        dll.addAtBeginning(20);
        dll.addAtEnd(30);
        dll.addAtEnd(40);
        dll.printList();
        dll.removeByData(40);
        dll.printList();
        dll.removeByIndex(2);
        dll.printList();
    }

    public void stackTest() {
        Stack stack = new Stack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
    }

    public void mergeSortTest() {
        int[] array = {44, 55, 12, 42, 94, 18, 6, 67};
        System.out.println("Unsorted Array:");
        printArray(array);

        array = new MergeSort().mergeSort(array);

        System.out.println("\nSorted Array:");
        printArray(array);
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
