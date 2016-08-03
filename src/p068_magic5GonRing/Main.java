package p068_magic5GonRing;

/**
 * http://projecteuler.net/problem=68
 *
 * https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        int size = 10;
        int[] ring = new int[size];
        boolean[] numbersUsed = new boolean[size + 1];
        permutate(ring, 0, numbersUsed);
    }

    /**
     * The 3-gon ring 432 621 513 is encoded like [4,6,5,3,2,1], and it is built
     * in that order. The first half of the array contains the external nodes.
     *
     * @param ring
     * @param i     The index of the next node to insert
     * @param used  To check if a number has already been used in the ring
     * @return      True if the solution has been found
     */
    private static boolean permutate(int[] ring, int i, boolean[] used) {
        if (i == ring.length)
            return isSolution(ring);

        for (int j = used.length - 1; j > 0; j--) {
            if (used[j]) continue;
            // Prune branch if the first node is not the smallest external node
            if (i == 0 || i >= ring.length/2 || ring[0] < j) {
                ring[i] = j;
                used[j] = true;
                if (permutate(ring, i+1, used))
                    return true;
                used[j] = false;     // Backtrack
            }
        }
        return false;
    }

    /**
     * Check if every line in the ring adds to the same result
     */
    private static boolean isSolution(int[] ring) {
        int half = ring.length / 2,     // Limit between inner and outer nodes
            sum = ring[0] + ring[half] + ring[half + 1];  // Sum of first line
        for (int i = 1; i < half; i++)
            if (sum != ring[i] + ring[half+i] + ring[(half+i+1) % half + half])
                return false;
        printRing(ring);
        return true;
    }

    private static void printRing(int[] ring) {
        int half = ring.length / 2;
        for (int i = 0; i < half; i++) {
            System.out.print(ring[i]);
            System.out.print(ring[half+i]);
            System.out.print(ring[(half+i+1) % half + half]);
        }
        System.out.println();
    }
}
