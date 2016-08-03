package p075_singularIntegerRightTriangles;

import commons.Numbers;

/**
 * http://projecteuler.net/problem=75
 *
 * https://github.com/albur
 */
public class Main {

    // http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple
    public static void main(String[] args) {
        int len1,                               // Perimeter when k = 1
            count = 0,                          // Number of solutions
            limit = 1500000;                    // Maximum length allowed
        short[] found = new short[limit+1];     // Statuses of the solutions
        boolean done = false;
        long ini = System.currentTimeMillis();
        for (int m = 2; !done; m++) {
            for (int n = 1; n < m && !done; n++) {
                if ((m-n) % 2 == 0 || Numbers.gcd(m, n) > 1)
                    continue;
                len1 = 2*m*(m+n);               // a=m²-n², b=2mn, c=m²-n²
                if (n == 1 && len1 > limit)     // All solutions have been found
                    done = true;
                for (int length = len1; length <= limit; length += len1) {
                    switch (++found[length]) {
                        case 1: count++; break; // Found once, add solution
                        case 2: count--;        // Found twice, discard it
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(count + "\n" + (end - ini) + " ms");
    }

}
