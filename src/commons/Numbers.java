package commons;

/**
 * Utility class for number operations
 *
 * @author https://github.com/albur
 */
public final class Numbers {

    /**
     * Uses the Sieve of Eratosthenes to produce the list of all primes under
     * the given limit.
     *
     * E.g. getPrimes(7) will return [2, 3, 5]
     */
    public static int[] getPrimesSmallerThan(int limit) {
        if (limit < 3)
            return new int[0];

        boolean[] isComposite = new boolean[limit];
        int size = limit - 2;   // Amount of primes (exclude indices 0 and 1)
        for (int i = 2; i*i < limit; i++)
            if (!isComposite[i])
                for (int j = i; i*j < limit; j++)
                    if (!isComposite[i*j]) {
                        size--;
                        isComposite[i*j] = true;
                    }

        int[] primes = new int[size];
        for (int i = 2, j = 0; i < limit; i++)
            if (!isComposite[i]) {
                primes[j] = i;
                j++;
            }
        return primes;
    }

    /**
     * Returns true if the number is prime
     */
    public static boolean isPrime(int num) {
        if (num < 2)
            return false;
        if (num == 2)
            return true;
        if (num % 2 == 0)
            return false;
        for (int i = 3; i*i <= num; i += 2)
            if (num % i == 0)
                return false;
        return true;
    }

    /**
     * Check if a number contains every digit from 1 to 9 exactly once.
     */
    public static boolean isZerolessPandigital(int n) {
        if (n < 123456789 || n > 987654321)
            return false;

        boolean[] digits = new boolean[9];
        int d;
        while (n > 0) {
            d = n % 10;
            if (d == 0 || digits[d - 1])
                return false;
            digits[d - 1] = true;
            n /= 10;
        }
        return true;
    }

    /**
     * Uses Euclid's algorightm to calculate the greatest common divisor
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
