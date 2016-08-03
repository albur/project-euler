package p064_oddPeriodSquareRoots;

/**
 * http://projecteuler.net/problem=64
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        long ini = System.currentTimeMillis();

        double sq;
        int count = 0;
        for (int n = 2; n <= 10000; n++) {
            sq = Math.sqrt(n);
            if (sq != Math.floor(sq) && getPeriod(n) % 2 != 0)
                count++;
        }

        long end = System.currentTimeMillis();
        System.out.println(count + "\n" + (end -ini) + " ms");
    }

    // http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
    public static int getPeriod(int n) {
        int sq = (int) Math.sqrt(n);
        int m = 0;
        int d = 1;
        int a = sq;
        int m1 = 0, d1 = 0;

        for (int period = 0; ; period++) {
            m = d * a - m;
            d = (n - m * m) / d;
            a = (sq + m) / d;

            if (period == 0) {                 // Store initial conditions
                m1 = m;
                d1 = d;
            } else if (m == m1 && d == d1) { // Same conditions: end of cycle,
                return period;               // because a depends on m and d
            }
        }
    }
}
