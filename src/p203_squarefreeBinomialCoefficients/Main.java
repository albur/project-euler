package p203_squarefreeBinomialCoefficients;

import java.util.HashSet;
import java.util.Set;

import commons.Numbers;

/**
 * http://projecteuler.net/problem=203
 *
 * @author https://github.com/albur
 */
public class Main {

    public static int[] primes = Numbers.getPrimesSmallerThan(25);

    public static void main(String[] args) {
        int size = 51;
        long[][] triangle = new long[size][size]; // Pascal's Triangle
        Set<Long> sqFound = new HashSet<>();      // Distinct square-free
        long sqFreeSum = 1;                       // Sum of the square-free

        long ini = System.currentTimeMillis();

        // Initialize the triangle
        for (int i = 0; i < size; i++) {
            triangle[i][0] = triangle[i][i] = 1;
        }

        // Fill in the rest of values
        for (int y = 2; y < size; y++) {
            for (int x = 1; x < y; x++) {
                triangle[y][x] = triangle[y-1][x-1] + triangle[y-1][x];
                // Deal with square-free numbers
                if (isSqFree(triangle[y][x]) && sqFound.add(triangle[y][x])) {
                    sqFreeSum += triangle[y][x];
                }
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(sqFreeSum + "\n" + (end - ini) + " ms");
    }

    public static boolean isSqFree(long num) {
        for (long prime : primes) {
            if (num % (prime*prime) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void printTriangle(long[][] triangle) {
        for (long[] row : triangle) {
            for (long v : row) {
                System.out.print(String.format("%-4s", Long.toString(v)));
            }
            System.out.println();
        }
    }
}
