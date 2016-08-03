package p205_diceGame;

/**
 * http://projecteuler.net/problem=205
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        int[] pyraWaysToGet = new int[37];
        int[] cubeWaysToGet = new int[37];
        waysToGet(pyraWaysToGet, new int[9], 4, 0, 0);
        waysToGet(cubeWaysToGet, new int[6], 6, 0, 0);

        long rounds = (long) (Math.pow(4, 9) * Math.pow(6, 6));
        long pyraWaysToWin = 0;
        for (int p = 9; p < 37; p++)
            for (int c = 6; c < p; c++)
                pyraWaysToWin += pyraWaysToGet[p] * cubeWaysToGet[c];

        System.out.println(String.format("%.7f", (double)pyraWaysToWin/rounds));
    }

    /**
     * Given a group of dice with equal number of sides, calculate the number
     * of ways in which each total result can be obtained.
     *
     * @param ways   The number of ways to obtain each result
     * @param dice   The dice to throw
     * @param sides  The number of sides in a die
     * @param die    The index of the current die
     * @param result The sum of the individual results
     */
    public static void waysToGet(int[] ways, int[] dice, int sides, int die,
            int result) {
        if (die == dice.length)
            ways[result]++;
        else
            for (dice[die] = 1; dice[die] <= sides; dice[die]++)
                waysToGet(ways, dice, sides, die + 1, result + dice[die]);
    }

}
