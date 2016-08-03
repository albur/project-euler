package p173_hollowSquareLaminae;

/**
 * http://projecteuler.net/problem=173
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        int count = 0;
        int tiles = 1000000;
        int maxSide = (tiles + 4) / 4;

        long ini = System.currentTimeMillis();

        for (int side = 3; side <= maxSide; side++) {
            count++;
            int used = side * 4 - 4;
            for (int sub = side - 2; sub > 2; sub -= 2) {
                used += sub * 4 - 4;
                if (used > tiles) break;
                count++;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(count + "\n" + (end - ini) + " ms");
    }

}
