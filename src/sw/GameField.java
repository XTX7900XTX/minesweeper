package sw;

import java.awt.Point;
import java.util.Random;

public class GameField {
	
	private static final int MINE = -1;

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

    public static void setRandom(Random customRand) {
        rand = customRand;
    }

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

    private static int[][] createMineField(int rows, int cols, int mines) {
        int[][] field = new int[rows][cols];
        if (mines >= rows * cols) {
        	mines = rows * cols;
        }
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            Point random = getRandomCell(field);
            while (field[random.x][random.y] == MINE) {
                random = getRandomCell(field);
            }
            field[random.x][random.y] = MINE;
            minesPlaced++;
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
        int[] directions = {-1, 0, 1}; // 相對位置
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                if (field[r][c] == MINE) continue;
                
                int count = 0;
                for (int dr : directions) {
                    for (int dc : directions) {
                        if (dr == 0 && dc == 0) continue;
                        int nr = r + dr, nc = c + dc;
                        if (nr >= 0 && nr < field.length && nc >= 0 && nc < field[r].length && field[nr][nc] == -1) {
                            count++;
                        }
                    }
                }
                field[r][c] = count;
            }
        }
    }
    
}
