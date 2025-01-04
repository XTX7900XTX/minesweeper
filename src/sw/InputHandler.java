package sw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
	private static Scanner userInput = new Scanner(System.in);
    public static void setScanner(Scanner scanner) {
        userInput = scanner;
    }
    
	private static void checkQuitKey(String input) {
	    if (input.contentEquals("q")) {
	        System.exit(0);
	    }
	}

	public static boolean gamemode() {
        String choice = "foo";
        String validChoices = "NnYy";
        while (validChoices.indexOf(choice) == -1) {
            System.out.print("Do you want to play in debug mode? "
            		+ "In debug mode, you can see the value of each cell in the "
            		+ "mine field. "   
            		+ "(Y/n) => ");
            choice = userInput.nextLine().toLowerCase();
            checkQuitKey(choice);
            if (validChoices.indexOf(choice) != -1)
            	return choice.contentEquals("y");
            System.out.println("Please enter y or n.");
        }
        return false;
	}
	
	
	public static int[] gameConfig() {
        int rows = 0;
        int cols = 0;
        while (rows == 0 || cols == 0) {
            System.out.print("Enter number of rows then columns (ex: 4 4)=> ");
            try {
                rows = userInput.nextInt();
                cols = userInput.nextInt();
                if (rows <= 0 || cols <= 0) {
                    System.out.println("Please enter two valid non-zero numbers.");
                }
            }
            catch (InputMismatchException e) {
                String invalid = userInput.nextLine().toLowerCase();
                checkQuitKey(invalid);
                System.out.println("Please enter two valid non-zero numbers.");
            }
        }
        

        int mines = 0;
        while (mines == 0) {
            System.out.print("Enter number of mines=> ");
            try {
                mines = userInput.nextInt();
                if (mines <= 0) {
                    System.out.println("Please enter a valid non-zero number.");
                }
                else if (rows * cols > 1 && mines >= rows * cols) {
                    System.out.println(
                        "Too many mines! Must be less than " + (rows * cols)
                            + ".");
                    mines = 0;
                }
            }
            catch (InputMismatchException e) {
                String invalid = userInput.nextLine().toLowerCase();
                checkQuitKey(invalid);

                System.out.println("Please enter a valid non-zero number.");
            }
        }
        return new int[] {rows,cols,mines};
	}

	public static int[] inGameSweep(int[][] field) {
        int row = -1;
        int col = -1;
        while (row == -1 || col == -1) {
            System.out.print(
                "Enter a row and column value "
                    + "(0 0 is top left corner) => ");
            try {
                row = userInput.nextInt();
                col = userInput.nextInt();

                if (row < 0 || col < 0 || row > field.length
                    || col > field[0].length) {
                    System.out.println("Please enter valid coordinates.");
                    row = -1;
                    col = -1;
                }
            }
            catch (InputMismatchException e) {
                String invalid = userInput.nextLine().toLowerCase();
                checkQuitKey(invalid);
                System.out.println("Please enter valid coordinates.");
            }
        }
		return new int[] {row,col};
	}
	
    public static void closeScanner() {
        if (userInput != null) {
        	userInput.close();
        }
    }
}
