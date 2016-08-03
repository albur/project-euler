package p083_PathSumFourWays;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * http://projecteuler.net/problem=83
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int[][] matrix = readMatrix("matrix.txt", 80);
        long ini = System.currentTimeMillis();
        int minimalPath = dijkstra(matrix);
        long end = System.currentTimeMillis();
        System.out.println(minimalPath + "\n" + (end-ini) + " ms");
    }

    /**
     * Use Dijkstra's algorithm to calculate the cost of the shortest path from
     * any the top left node to the bottom right node.
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
                cost[i][j] = Integer.MAX_VALUE;
        cost[0][0] = matrix[0][0];
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
            if (j > 0 && cost[i][j] + matrix[i][j-1] < cost[i][j-1]) {
                cost[i][j-1] = cost[i][j] + matrix[i][j-1];
            }
        }
        return cost[size-1][size-1];
    }

    public static int[][] readMatrix(String path, int size) {
        int[][] matrix = new int[size][size];
        String line;
        String[] row;
        int i = 0;
        Path p = Paths.get(Main.class.getResource(path).getPath());
        try (BufferedReader br = Files.newBufferedReader(p, UTF_8)) {
        while ((line = br.readLine()) != null) {
            row = line.split(",");
            for (int j = 0; j < size; j++)
                matrix[i][j] = Integer.parseInt(row[j]);
            i++;
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
