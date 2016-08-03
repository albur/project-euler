package p096_sudoku;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * http://projecteuler.net/problem=96
 *
 * @author https://github.com/albur
 */
public class Main {

    public static void main(String[] args) {
        Path p = Paths.get(Main.class.getResource("sudoku.txt").getPath());
        try (BufferedReader br = Files.newBufferedReader(p, UTF_8)) {
            ArrayList<Sudoku> sudokus = new ArrayList<>();
            int[][] board = null;
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                if (line.indexOf('G') == 0) {
                    if (board != null) {
                        sudokus.add(new Sudoku(board));
                    }
                    board = new int[9][9];
                    row = 0;
                } else {
                    for (int j = 0; j < 9; j++) {
                        board[row][j] = Character.digit(line.charAt(j), 10);
                    }
                    row++;
                }
            }
            sudokus.add(new Sudoku(board));

            long ini = System.currentTimeMillis();
            int sum = 0;
            for (Sudoku sudoku : sudokus) {
                sudoku.solve();
                sum += sudoku.getTopLeft();
            }
            long end = System.currentTimeMillis();
            System.out.println(sum + "\n" + (end - ini) + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
