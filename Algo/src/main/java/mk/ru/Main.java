package mk.ru;


import mk.ru.graph.*;

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
//        test.binaryTreeTest();
//        test.binaryHeapTest();
//        test.singleLinkedListTest();
//        test.doubleLinkedListTest();
//        test.stackTest();
//        test.mergeSortTest();

        GraphMatrix g = new GraphMatrix(9);

        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.addEdge(3, 4);
        g.addEdge(4, 5);

        g.addEdge(1, 6);
        g.addEdge(6, 7);
        g.addEdge(6, 8);

//        g.printGraph();

        g.BFS(1);
//        g.BFS(1);
    }
}