package p066_diophantineEquation;

import java.math.BigInteger;

/**
 * http://projecteuler.net/problem=66
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        long ini = System.currentTimeMillis();
        int maxD = 0;
        BigInteger x, maxX = BigInteger.ZERO;
        for (int D = 2; D <= 1000; D++) {
            if ((x = converge(D)).compareTo(maxX) > 0) {
                maxX = x;
                maxD = D;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(maxD + "\n" + (end-ini) + " ms");
    }

    // http://en.wikipedia.org/wiki/Pell's_equation#Fundamental_solution_via_continued_fractions
    // (1) http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
    // (2) http://en.wikipedia.org/wiki/Continued_fraction#Infinite_continued_fractions
    public static BigInteger converge(int n) {
        double sqrt = Math.sqrt(n);
        if (sqrt == (int) sqrt)
            return BigInteger.ZERO;

        BigInteger
            D  = BigInteger.valueOf(n),
            sq = BigInteger.valueOf((int) sqrt),
            m  = BigInteger.ZERO,
            d  = BigInteger.ONE,
            a  = sq,
            hA, hB = BigInteger.ONE,  h = a,               // Numerators
            kA, kB = BigInteger.ZERO, k = BigInteger.ONE;  // Denominators

        while(!h.pow(2).subtract(D.multiply(k.pow(2))).equals(BigInteger.ONE)) {
            // (1) Expand continued fraction
            m = d.multiply(a).subtract(m);
            d = D.subtract(m.pow(2)).divide(d);
            a = sq.add(m).divide(d);
            // (2) Calculate numerator and denominator
            hA = hB;
            kA = kB;
            hB = h;
            kB = k;
            h = a.multiply(hB).add(hA);
            k = a.multiply(kB).add(kA);
        }
        return h;
    }
}
