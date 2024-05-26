package mk.ru.graph;

import java.util.*;

public class GraphList {
    private final int V;
    private final LinkedList<Integer>[] adj;

    GraphList(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) adj[i] = new LinkedList<>();
    }

    // Функция добавления ребра
    void addEdge(int v, int w) {
        adj[v].add(w); // Не добавляем обратное ребро, так как граф ненаправленный
    }

    // Обход в ширину
    void BFS(int s) {
        boolean[] visited = new boolean[V]; // Массив для отслеживания посещенных вершин
        Queue<Integer> queue = new LinkedList<>(); // Очередь для BFS

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            for (int n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }

            System.out.println();
        }
    }

    // Обход в глубину
    void DFS(int v) {
        boolean[] visited = new boolean[V]; // Массив для отслеживания посещенных вершин
        Stack<Integer> stack = new Stack<>(); // Стек для DFS

        visited[v] = true;
        stack.push(v);

        while (!stack.isEmpty()) {
            v = stack.pop();
            System.out.print(v + " ");

            for (int n : adj[v]) {
                if (!visited[n]) {
                    visited[n] = true;
                    stack.push(n);
                }
            }
        }
    }

    void printGraph() {
        for (int v = 0; v < V; ++v) {
            System.out.print(v + ": ");
            for (int w : adj[v])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
