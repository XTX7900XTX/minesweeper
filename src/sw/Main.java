package sw;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to MineField. Enter 'q' at any time to quit.");
        GameSession newgame = new GameSession();
        newgame.start();
        System.out.println("Thank you for playing.");
    }
}