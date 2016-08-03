package p065_convergentsOfE;

import java.math.BigInteger;

/**
 * http://projecteuler.net/problem=65
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        String numerator = convergentOfE(100)[0].toString();
        int sum = 0;
        for (int i = 0; i < numerator.length(); i++) {
            sum += Character.digit(numerator.charAt(i), 10);
        }
        System.out.println(sum);
    }

    /**
     * e = [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...]
     *
     * @param nth   The convergent to calculate
     */
    public static BigInteger[] convergentOfE(int nth) {
        BigInteger[] fraction = new BigInteger[2];
        BigInteger left, den;
        for (int pos = nth; pos > 0; pos--) {
            if (pos == 1) {
                left = BigInteger.valueOf(2);
            } else if (pos % 3 == 0) {
                left = BigInteger.valueOf(pos / 3 * 2);
            } else {
                left = BigInteger.ONE;
            }

            if (pos == nth) {
                fraction[0] = left;
                fraction[1] = BigInteger.ONE;
            } else {
                den = fraction[1];
                fraction[1] = fraction[0];
                fraction[0] = den.add(left.multiply(fraction[1]));
            }
        }
        return fraction;
    }
}
