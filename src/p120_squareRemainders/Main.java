package p120_squareRemainders;

/**
 * http://projecteuler.net/problem=120
 *
 * @author https://github.com/albur
 */
public class Main {
    public static void main(String[] args) {
        int sum = 0;
        for (int a = 3; a <= 1000; a++) {
            sum += a * (a % 2 == 0 ? a - 2 : a -1);
        }
        System.out.println(sum);
    }

}
