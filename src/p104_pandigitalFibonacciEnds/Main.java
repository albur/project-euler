package p104_pandigitalFibonacciEnds;

import commons.Numbers;

/**
 * http://projecteuler.net/problem=104
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        long aHead = 0, aTail = 0,      // a = n - 2
             bHead = 1, bTail = 1,      // b = n - 1
             aux;

        long ini = System.currentTimeMillis();
        for (int n = 2; ; n++) {
            // Fibonacci for the tails
            aux = bTail;
            bTail = (bTail + aTail) % ((long) 1e9);
            aTail = aux;

            // Fibonacci for the heads
            aux = bHead;
            bHead += aHead;
            aHead = aux;
            if (bHead >= ((long) 1e15)) {   // Less precision is not enough
                bHead /= 10;
                aHead /= 10;
            }

            if (Numbers.isZerolessPandigital((int) bTail)) {
                aux = bHead / 1000000;      // Use only the first 9 digits
                if (Numbers.isZerolessPandigital((int) aux)) {
                    System.out.println(n);
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - ini + " ms");
    }
}
