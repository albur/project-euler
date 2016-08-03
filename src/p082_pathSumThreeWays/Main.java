package p082_pathSumThreeWays;

import java.io.IOException;

/**
 * http://projecteuler.net/problem=82
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int[][] matrix = p083_PathSumFourWays.Main.readMatrix("matrix.txt", 80);
        long ini = System.currentTimeMillis();
        int minimalPath = dijkstra(matrix);
        long end = System.currentTimeMillis();
        System.out.println(minimalPath + "\n" + (end-ini) + " ms");
    }

    /**
     * Use Dijkstra's algorithm to calculate the cost of the shortest path from
     * any node in the left column to any node in the right column, and only
     * moving up, down, and right.
     *
     * @param matrix The cost of visiting each node
     * @return       The weight of the shortest path
     */
    public static int dijkstra(int[][] matrix) {
        int size = matrix.length;
        boolean[][] visited = new boolean[size][size];
        int[][] cost = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (j == 0) cost[i][j] = matrix[i][j];   // Relax first column
                else        cost[i][j] = Integer.MAX_VALUE;
        int i = 0, j = 0, bestCost = 0;
        while (bestCost != Integer.MAX_VALUE) {
            // Find the cheapest unvisited node to visit
            bestCost = Integer.MAX_VALUE;
            for (int a = 0; a < size; a++)
                for (int b = 0; b < size; b++)
                    if (!visited[a][b] && cost[a][b] < bestCost) {
                        bestCost = cost[a][b];
                        i = a;
                        j = b;
                    }
            visited[i][j] = true;
            // Relax it's neighbors
            if (i > 0 && cost[i][j] + matrix[i-1][j] < cost[i-1][j]) {
                cost[i-1][j] = cost[i][j] + matrix[i-1][j];
            }
            if (i+1 < size && cost[i][j] + matrix[i+1][j] < cost[i+1][j]) {
                cost[i+1][j] = cost[i][j] + matrix[i+1][j];
            }
            if (j+1 < size && cost[i][j] + matrix[i][j+1] < cost[i][j+1]) {
                cost[i][j+1] = cost[i][j] + matrix[i][j+1];
            }
        }
        // Return the minimum cost found in the right column
        int min = cost[0][size-1];
        for (i = 1; i < size; i++)
            if (cost[i][size-1] < min)
                min = cost[i][size-1];
        return min;
    }
}

