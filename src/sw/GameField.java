package sw;

import java.awt.Point;
import java.util.Random;

public class GameField {
    private boolean[][] exposed;
    private int[][] field;
    GameField(int rows,int cols,int mines){
        // exposed tracks which cells have been exposed.
        // True if the cell is exposed, false otherwise.
        this.exposed = setUpExposed(rows, cols);

        // field is the game board. mines are -1. other values represent
        // the number of (eight connected) neighboring mines
        this.field = createMineField(rows, cols, mines);
    }
    
    public int[][] getField(){
    	return this.field;
    }
    
    public boolean[][] getExposed(){
    	return this.exposed;
    }
    
    /** Random number generator used to get random cells */
    private static Random rand = new Random();

    /**
     * Creates a 2D-array of false values of size rows x cols.
     * This array is used to track which cells in the actual mine field have 
     * been exposed.
     * 
     * @param rows
     *            number of rows in the mine field
     * @param cols
     *            number of columns in the mine field
     * @return a 2D-boolean-array (size rows x cols), all equal to false
     */
    private static boolean[][] setUpExposed(int rows, int cols) {
        boolean[][] array = new boolean[rows][cols];
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                array[r][c] = false;
            }
        }

        return array;
    }

    /**
     * Create a 2D-array of size rows x cols with the game setup. 
     * Cells will have the following values: 
     * <ul>
     * 	    <li> -1 if the cell is a mine </li>
     *      <li> 0 if the cell is not a mine, and the cell has no mines as neighbors </li>
     *      <li> 1..8, 1 for each mine that the current cell touches. </li> 
     * </ul>
     * 
     * This method calls getRandomCell to choose random places for the mines, 
     * and setHint to set the value for the non-mine cells.
     * 
     * @param rows
     *            number of rows in the mine field
     * @param cols
     *            number of columns in the mine field
     * @param mines
     *            number of mines in the mine field
     * @return a 2D-integer-array (size rows x cols) representing the 
     *         mine field game board
     */
    private static int[][] createMineField(int rows, int cols, int mines) {
        int[][] field = new int[rows][cols];

        // If mines >= the area of the field, set all cells to mines.
        // NOTE: Because of the way the main is set up, this will never happen.
        if (mines >= rows * cols) {
            for (int r = 0; r < field.length; r++) {
                for (int c = 0; c < field[r].length; c++) {
                    field[r][c] = -1;
                }
            }
        }
        // If mines take up more than half the field, temporarily set all cells
        // to mines, then select random cells to remove mines from.
        else if (mines > 0.5 * rows * cols) {
            for (int r = 0; r < field.length; r++) {
                for (int c = 0; c < field[r].length; c++) {
                    field[r][c] = -1;
                }
            }

            int minesRemoved = 0;
            while (minesRemoved < rows * cols - mines) {
                Point random = getRandomCell(field);
                while (field[random.x][random.y] != -1) {
                    random = getRandomCell(field);
                }

                field[random.x][random.y] = 0;
                minesRemoved++;
            }
        }
        // If mines take up less than half the field, select random cells to
        // have mines.
        else {
            int minesPlaced = 0;
            while (minesPlaced < mines) {
                Point random = getRandomCell(field);
                while (field[random.x][random.y] == -1) {
                    random = getRandomCell(field);
                }

                field[random.x][random.y] = -1;
                minesPlaced++;
            }
        }

        setHint(field);

        return field;
    }

    /**
     * Helper method to create random point within 2D-array bounds. 
     * A Point object is an easy way to store x, y values in one object.
     * 
     * @param field
     *            the MineField from which a random point should be chosen
     * @return random location within array
     */
    private static Point getRandomCell(int[][] field) {
        int x = rand.nextInt(field.length);
        int y = rand.nextInt(field[0].length);

        return new Point(x, y);
    }

    /**
     * Given a mine field (2D-integer-array with -1 for mines), set the value 
     * of all non-negative cells to the number of mines they border (0-8).
     * 
     * @param field
     *          the mine field that contains the mines; all other values in this 
     *          field will be changed to reflect the number of adjacent mines
     */
    private static void setHint(int[][] field) {
        int lastRow = field.length - 1;
        for (int r = 0; r < field.length; r++) {
            int lastCol = field[r].length - 1;
            for (int c = 0; c < field[r].length; c++) {
                // Don't set a hint if the cell is a mine.
                if (field[r][c] == -1) {
                    continue;
                }

                // Otherwise, count adjacent mines.
                int count = 0;
                if (r != 0 && field[r - 1][c] == -1) {
                    count++;
                }
                if (r != 0 && c != lastCol && field[r - 1][c + 1] == -1) {
                    count++;
                }
                if (c != lastCol && field[r][c + 1] == -1) {
                    count++;
                }
                if (r != lastRow && c != lastCol && field[r + 1][c + 1] == -1) {
                    count++;
                }
                if (r != lastRow && field[r + 1][c] == -1) {
                    count++;
                }
                if (r != lastRow && c != 0 && field[r + 1][c - 1] == -1) {
                    count++;
                }
                if (c != 0 && field[r][c - 1] == -1) {
                    count++;
                }
                if (r != 0 && c != 0 && field[r - 1][c - 1] == -1) {
                    count++;
                }

                // Set the value of the cell to the number of adjacent mines.
                field[r][c] = count;
            }
        }
    }
    
}
