package sw;

public class GameWinLogic {
    /**
     * Expose cell exposes specified cell. If a mine, returns false (i.e., we
     * dead); if a hint, returns true; if zero, then exposes all neighbors until
     * hints are exposed.
     *
     * @param row
     *          cell row
     * @param col
     *          cell column
     * @param field
     *          the mine field
     * @param exposed
     *          2D array with whether a cell is exposed. exposed[row][col]
     *          should be set to true in this method.
     * @return false if we hit a mine, true otherwise
     */
    private static  boolean exposeCell(int row, int col, int[][] field, boolean[][] exposed) {

        if (field == null) {
            return false;
        }

        exposed[row][col] = true;
        if (field[row][col] < 0) {
            // Stop recursion if mine
            return false;
        }

        if (field[row][col] > 0) {
            // Stop recursion if neighboring a mine
            return true;
        }

        // Must be zero
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue; // same cell
                }
                // Point cell = new Point(row + i, col + j);
                int curRow = row + i;
                int curCol = col + j;
                if (curRow >= 0 && curRow < field.length) {
                    if (curCol >= 0 && curCol < field[0].length) {
                        // valid cell
                        if (!exposed[curRow][curCol]) {
                            // Make recursive call if and only if
                            // the cell is not already exposed
                            exposeCell(curRow, curCol, field, exposed);
                        }
                    }

                }
            }
        }

        return true;
    }

    /**
     * Return true if the arrays indicate a win, false otherwise. A player wins
     * if they have exposed all non-mine cells.
     * 
     * @param field
     *            the mine field
     * @param exposed
     *            the cells that are exposed
     * @return true for a win, false otherwise.
     */
    private static boolean won(int[][] field, boolean[][] exposed) {
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if ((field[r][c] != -1 && !exposed[r][c]) || (field[r][c] == -1 && exposed[r][c])) {
                    return false;
                }
            }
        }
        return true;
    }

	public static void gameLoop(int[][] field,boolean[][] exposed,boolean mode) {
		OutputHandler.printBoards(field, exposed, mode);
        while (!won(field, exposed)) {
    		int[] digPosition =InputHandler.inGameSweep(field);
    		int row = digPosition[0];
    		int col = digPosition[1];

            if (!exposeCell(row, col, field, exposed)) {
                System.out.println(OutputHandler.fieldToString(field) + "You lose. Sorry.");
                System.exit(0);
            }
            OutputHandler.printBoards(field, exposed, mode);
        }

        if (won(field, exposed)) {
            System.out.println(OutputHandler.fieldToString(field) + "You won!!!");
        }
	}
}
