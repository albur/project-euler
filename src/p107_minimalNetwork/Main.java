package p107_minimalNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * http://projecteuler.net/problem=107
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // Read the graph and calculate its total weight
        BufferedReader br = new BufferedReader(new InputStreamReader(
                Main.class.getResourceAsStream("network.txt")));
        int[][] weights = new int[40][40];  // The adjacency matrix
        int i = 0, graphWeight = 0;
        String line;
        String[] row;
        while ((line = br.readLine()) != null) {
            row = line.split(",");
            for (int j = 0; j < row.length; j++) {
                if (row[j].equals("-"))
                    weights[i][j] = 0;
                else {
                    weights[i][j] = Integer.parseInt(row[j]);
                    graphWeight += weights[i][j];
                }
            }
            i++;
        }
        br.close();
        graphWeight /= 2;    // Undirected graph
        System.out.println((graphWeight - prim(weights)));
    }

    /**
     * Use Prim's algorithm to calculate the weight of the minimum spanning tree
     *
     * @param weights The adjacency matrix of a weighted undirected graph
     * @return The weight of the minimum spanning tree
     */
    public static int prim(int[][] weights) {
        int size = weights.length;
        boolean[] inTree = new boolean[size]; // Vertices in the MST
        inTree[0] = true;                     // Add a random vertex to start
        int vAdded = 1,                       // Number of vertices in the MST
            totalWeight = 0,                  // Weight of the MST
            bestV = 0,                        // Candidate vertex
            bestW;                            // Candidate edge weight
        while (vAdded < size) {
            bestW = Integer.MAX_VALUE;
            // Find the cheapest edge that links one of the vertices in the MST
            // to an outside vertex
            for (int u = 0; u < size; u++) {
                if (inTree[u]) {
                    for (int v = 0; v < size; v++) {
                        if (!inTree[v]
                                && weights[u][v] > 0
                                && weights[u][v] < bestW) {
                            bestV = v;
                            bestW = weights[u][v];
                        }
                    }
                }
            }
            inTree[bestV] = true;   // Add vertex
            totalWeight += bestW;   // And the weight of the edge
            vAdded++;
        }
        return totalWeight;
    }
}
