package p089_romanNumerals;

/**
 * http://projecteuler.net/about=roman_numerals
 *
 * @author https://github.com/albur
 */
public class RomanNumeral {
    /**
     * Get the numeric value of a Roman numeral
     */
    public static int parse(String numeral) {
        int n = 0;      // The numeric value
        char p = '.';   // Previous character
        char[] chars = numeral.toCharArray();
        for (int i = chars.length-1; i >= 0; i--) {
            switch (chars[i]) {
            case 'I': n += p == 'V' || p == 'X' ? -1   : 1;   break;
            case 'V': n += 5;                                 break;
            case 'X': n += p == 'L' || p == 'C' ? -10  : 10;  break;
            case 'L': n += 50;                                break;
            case 'C': n += p == 'D' || p == 'M' ? -100 : 100; break;
            case 'D': n += 500;                               break;
            case 'M': n += 1000;                              break;
            }
            p = chars[i];
        }
        return n;
    }

    /**
     * Get the Roman numeral representation of a number
     */
    public static String encode(int number) {
        StringBuilder s = new StringBuilder();  // The Roman numeral
        for (Weight w : Weight.values())
            while (number >= w.weight) {
                s.append(w);
                number -= w.weight;
            }
        return s.toString();
    }

    private enum Weight {
        M(1000), DM(900), D(500), CD(400), C(100), XC(90), L(50), XL(40), X(10),
        IX(9), V(5), IV(4), I(1);
        private int weight;
        Weight(int v) {
            this.weight = v;
        }
    }
}
