package p078_coinPartitions;

import java.util.ArrayList;

/**
 * http://projecteuler.net/problem=78
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        long ini = System.currentTimeMillis();

        // Pre-calculate generalized pentagonal numbers using the formula:
        // n * (3*n - 1) / 2. Where n = 0, ±1, ±2, ...
        // http://mathworld.wolfram.com/PentagonalNumber.html
        ArrayList<Integer> pent = new ArrayList<>();    // Pentagonal numbers
        int limit = 100000, n = 0;
        do {
            pent.add(n*(3*n-1)/2);
            pent.add(-n*(-3*n-1)/2);
        } while (pent.get(2*n++) < limit);

        // Calculate partitions using the generating function:
        // p(n) = p(n−1) + p(n−2) − p(n−5) − p(n−7) + p(n−12) + p(n−15) − ...
        // en.wikipedia.org/wiki/Partition_(number_theory)#Generating_function
        int[] part = new int[limit];   // Partitions
        part[0] = 1;                   // By convention
        for (n = 1; (part[n-1] %= 1000000) != 0 ; n++) {
            int i = 1, p = pent.get(i); // i: addend's index, p: ith pentagonal
            do {
                part[n] += (i-1)%4 > 1 ? part[n-p] : -part[n-p]; // +--++--++--
            } while(n >= (p = pent.get(i++)));
        }
        long end = System.currentTimeMillis();
        System.out.println((n-1) + "\n" + (end - ini) + " ms");
    }

}
