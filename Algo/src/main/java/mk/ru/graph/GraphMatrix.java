package mk.ru.graph;

import java.util.*;

public class GraphMatrix {
    private int V; // Количество вершин
    private int[][] adjMatrix; // Матрица смежности

    public GraphMatrix(int V) {
        this.V = V;
        adjMatrix = new int[V][V]; // Инициализация матрицы смежности нулями
    }

    // Функция добавления ребра между двумя вершинами
    void addEdge(int v, int w) {
        adjMatrix[v][w] = 1; // Устанавливаем значение 1, если есть ребро между v и w
        adjMatrix[w][v] = 1; // Граф может быть несymmmetric, поэтому устанавливаем обратное ребро
    }

    // Обход в ширину
    void BFS(int s) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            for (int i = 0; i < V; i++) {
                if (adjMatrix[s][i] == 1 &&!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    // Обход в глубину
    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 0; i < V; i++) {
            if (adjMatrix[v][i] == 1 &&!visited[i]) {
                DFSUtil(i, visited);
            }
        }
    }

    // Публичный метод для вызова DFS
    void DFS(int startVertex) {
        boolean[] visited = new boolean[V];
        DFSUtil(startVertex, visited);
    }

}
