package TicTacToeInterface;

public interface TicTacToeInterface {
	void printBoard(char[][] board);

	void printInvalidMoveMessage();

	int promptPlayerMove();

	void printVictoryMessage(int currentPlayer);

	void printDrawMessage();

	void printGoodBye();

	boolean askPlayAgain();
}
