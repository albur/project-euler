package p077_primeSummations;

import commons.Numbers;

/**
 * http://projecteuler.net/problem=77
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        long ini = System.currentTimeMillis();

        int num = 2;
        while (sumOfPrimes(num, 0) <= 5000)
            num++;

        long end = System.currentTimeMillis();
        System.out.println(num + "\n" + (end - ini) + " ms");
    }

    private static int[] primes = Numbers.getPrimesSmallerThan(100);
    private static int[][] memo = new int[100][100];

    /**
     * Calculate in how many different ways it is possible to write a number as
     * the sum of primes.
     *
     * @param num       The number to be transformed in a sum of primes
     * @param ithPrime  Index of the 1st prime to substract (e.g. 2 is the 0th)
     * @return
     */
    private static int sumOfPrimes(int num, int ithPrime) {
        if (num <  0) return 0;
        if (num == 0) return 1;
        if (memo[num][ithPrime] > 0)
            return memo[num][ithPrime];

        for (int i = ithPrime; i < primes.length; i++) {
            if (primes[i] > num)
                break;
            memo[num][ithPrime] += sumOfPrimes(num - primes[i], i);
        }
        return memo[num][ithPrime];
    }
}
