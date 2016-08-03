package p096_sudoku;

/**
 * @author https://github.com/albur
 */
public class Sudoku {

    private int[][] board;
    private int[] moves;            // Legal numbers for a cell

    /**
     * @param board Should contain numbers from 0 (empty cell) to 9
     */
    public Sudoku(int[][] board) {
        this.board = board;
        this.moves = new int[9];
    }

    public boolean solve() {
        int[] cell = findCell();    // Get an empty cell to fill in
        if (cell[0] == -1) {        // No empty cells, the Sudoku is solved
            return true;
        }
        for (int n : moves) {
            if (n != 0) {
                board[cell[0]][cell[1]] = n; // Try first legal move
                if (solve()) {               // Right path, the Sudoku is solved
                    return true;
                }
                board[cell[0]][cell[1]] = 0; // Wrong path, backtrack
            }
        }
        return false;
    }

    /**
     * Find the easiest empty cell to solve. E.g. {6, 0}
     *
     * @return {-1, -1} if there are no empty cells
     */
    private int[] findCell() {
        int[] cell = {-1, -1};
        int[] moves;            // Legal numbers for the current cell
        int choicesMin = 10;    // Number of choices for the easiest cell so far
        int choicesTry;         // Number of choices for the current cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    moves = findMoves(i, j);
                    choicesTry = 0;
                    for (int n = 0; n < 9; n++)     // Count available choices
                        if (moves[n] != 0)
                            choicesTry++;
                    if (choicesTry < choicesMin) {  // Found better scenario
                        choicesMin = choicesTry;
                        cell[0] = i;
                        cell[1] = j;
                        this.moves = moves;
                    }
                    if (choicesTry == 1)            // Found best scenario
                        return cell;
                }
            }
        }
        return cell;
    }

    /**
     * Find legal numbers for an empty cell
     *
     * @return An array like { 1, 0, 0, 0, 5, 6, 0, 0, 0 }
     */
    private int[] findMoves(int i, int j) {
        int[] moves = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int iBox = i / 3;
        int jBox = j / 3;
        int xBox;
        for (int x = 0; x < 9; x++) {
            xBox = x / 3;
            for (int y = 0; y < 9; y++) {
                if (board[x][y] != 0
                     && (x == i || y == j
                      || (xBox == iBox && y / 3 == jBox))) {
                    moves[board[x][y] - 1] = 0;   // Remove illegal moves
                }
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s += board[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public int getTopLeft() {
        return board[0][0] * 100 + board[0][1] * 10 + board[0][2];
    }
}
