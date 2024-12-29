package sw;

public class OutputHandler {
	
    /**
     * Returns a single string with the contents of field. Each number should be 
     * formatted to take up 2 characters, and there should be a space around each
     * value.
     * 
     * @param field
     *          the mine field
     * @return Returns a string representation of the field array. For example,
     *         a 2x3 array with a bomb at 0,1 would return " 1 -1 1\n1 1 1\n"
     */
    public static String fieldToString(int[][] field) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                sb.append( String.format(" %2d ", field[r][c]) );
            }
            sb.append( System.lineSeparator() );
        }

        return sb.toString();
    }


    /**
     * Return a string representation of the mine field given 2D-arrays for the 
     * the mine field (integer-array) and which cells have been exposed in the 
     * field (boolean-array). 
     * 
     * Each row is on a separate line. Each cell is formatted to take up 4 
     * characters, with space around each cell.
     * 
     * The content of each cell will be:
     * <ul>
     *      <li> ' * ' if not exposed </li>
     *      <li> ' -1 ' if mine </li>
     *      <li> ' # ' where # is the number of adjacent mines </li>
     * </ul>
     * @param field
     *           the mine field
     * @param exposed
     *           a 2D-boolean-array indicating whether each cell has been exposed.
     * @return single string with each cell represented as * (if still hidden),
     *         -1 (if a mine), or n (a number indicating how many mines are
     *         around each cell.
     */
    private static String showBoard(int[][] field, boolean[][] exposed) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                // If the cell hasn't been exposed, it should be drawn as a '*'.
                if (!exposed[r][c]) {
                    sb.append( String.format(" %4s ", "*") );
                }
                else {
                    // If the cell equals zero, display a blank space.
                    if (field[r][c] == 0) {
                        sb.append( String.format(" %4s ", " ") );
                    }
                    // Otherwise display the number of the cell.
                    else {
                        sb.append( String.format(" %4d ", field[r][c]) );
                    }
                }
            }
            sb.append( System.lineSeparator() );
        }

        return sb.toString();
    }

    /**
     * Helper method prints the mine field every time the player guesses a cell. 
     * If the player is in debug mode, this method also prints a version of the 
     * mine field with all values exposed.
     * 
     * @param field
     *            the mine field to be printed
     * @param exposed
     *            boolean array that indicates which cells have been exposed.
     *            Needed to call showBoard.
     * @param debugOn
     *            if true, print the mine field and all its values
     */
    public static void printBoards(int[][] field, boolean[][] exposed, boolean debugOn) {
        System.out.println("\nBoard:\n" + showBoard(field, exposed));
        if (debugOn) {
            System.out.println(fieldToString(field));
        }
    }
}
