package p089_romanNumerals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * http://projecteuler.net/problem=89
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) throws IOException {
        InputStream stream = Main.class.getResourceAsStream("roman.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String line;
        int sum = 0;
        long ini = System.currentTimeMillis();
        while ((line = br.readLine()) != null) {
            sum += line.length()
                    - RomanNumeral.encode(RomanNumeral.parse(line)).length();
        }
        long end = System.currentTimeMillis();
        System.out.println(sum + "\n" + (end-ini) + " ms");
    }

}
