package sw;

public class GameSession {
    private int rows;
    private int cols;
    private int mines;
    private boolean mode;

	GameSession() {
		this.mode = InputHandler.gamemode();
        int[] gridSize = InputHandler.gameConfig();
        this.rows = gridSize[0];
        this.cols = gridSize[1];
        this.mines = gridSize[2];
	}

	public void start() {
		GameField field = new GameField(rows,cols,mines);
        GameWinLogic.gameLoop(field.getField(), field.getExposed(), mode);
        InputHandler.closeScanner();
	}
}