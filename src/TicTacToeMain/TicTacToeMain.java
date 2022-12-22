package TicTacToeMain;

import java.util.Random;

import TicTacToeInterface.*;

public class TicTacToeMain {
	private static char[][] board = new char[3][3];
	private static TicTacToeInterface view = new TicTacToeCLI();
	static int currentPlayer;
	
	private static void setupBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	static boolean checkVictory() {
		char currentPlayerChar = ' ';
		switch(currentPlayer) {
		case 1:
			currentPlayerChar = 'X';
			break;
		case -1:
			currentPlayerChar = 'O';
			break;
		default:
			break;
		}
		
		if(board[0][0] == currentPlayerChar && board[0][1] == currentPlayerChar && board[0][2] == currentPlayerChar) {
			return true;
		} else if(board[1][0] == currentPlayerChar && board[1][1] == currentPlayerChar && board[1][2] == currentPlayerChar) {
			return true;
		} else if(board[2][0] == currentPlayerChar && board[2][1] == currentPlayerChar && board[2][2] == currentPlayerChar) {
			return true;
		} else if(board[0][0] == currentPlayerChar && board[1][0] == currentPlayerChar && board[2][0] == currentPlayerChar) {
			return true;
		} else if(board[0][1] == currentPlayerChar && board[1][1] == currentPlayerChar && board[2][1] == currentPlayerChar) {
			return true;
		} else if(board[0][2] == currentPlayerChar && board[1][2] == currentPlayerChar && board[2][2] == currentPlayerChar) {
			return true;
		} else if(board[0][0] == currentPlayerChar && board[1][1] == currentPlayerChar && board[2][2] == currentPlayerChar) {
			return true;
		} else if(board[0][2] == currentPlayerChar && board[1][1] == currentPlayerChar && board[2][0] == currentPlayerChar) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean checkDraw() {
		for(char[] x : board) {
			for(char y: x) {
				if(y == ' ') {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean checkInvalidMove(int move) {
		if(move>8 || board[move/3][move%3] != ' ') {
			return true;
		} else {
			return false;
		}
	}
	
	static void getPlayermove() {
		int move;
		boolean moveNotValid;
		
		view.printBoard(board);
		do {
			move = view.promptPlayerMove();
			moveNotValid = checkInvalidMove(move);
			if(moveNotValid) {
				view.printInvalidMoveMessage();
			}
		} while(moveNotValid);
		
		board[move/3][move%3] = 'X';
	}
	
	static int score() {
		//if won return 1
		if(checkVictory()) {
			if(currentPlayer == -1) {
				return 1;
			} else {
				return -1;
			}
		}
	
		//if draw return 0
		if(checkDraw()) {
			return 0;
		}

		int bestScore;
		int candidateScore;
		char currentPlayerChar = ' ';
		
		//game continues, swap player and check best outcome for that player
		currentPlayer *= -1;
		
		switch(currentPlayer) {
		case 1:
			currentPlayerChar = 'X';
			break;
		case -1:
			currentPlayerChar = 'O';
			break;
		default:
			break;
		}
		
		if(currentPlayer == -1) {
			bestScore=-2;
			//iterate through all valid moves and score them
			for (int i = 0; i < 9; i++) {
				if(!checkInvalidMove(i)) {
					//try a move and see the outcome
					board[i/3][i%3] = currentPlayerChar;
					candidateScore = score();
					if(candidateScore>bestScore) {
						//save the outcome if it is better outcome
						bestScore = candidateScore;
					}
					//revert the board to before tried move
					board[i/3][i%3] = ' ';
				}
			}
		} else {
			bestScore=2;
			//iterate through all valid moves and score them
			for (int i = 0; i < 9; i++) {
				if(!checkInvalidMove(i)) {
					//try a move and see the outcome
					board[i/3][i%3] = currentPlayerChar;
					candidateScore = score();
					if(candidateScore<bestScore) {
						//save the outcome if it is better outcome
						bestScore = candidateScore;
					}
					//revert the board to before tried move
					board[i/3][i%3] = ' ';
				}
			}
			
		}
		currentPlayer *= -1;
		//return score based on computer perspective, best outcome for player is worst outcome for computer
		return bestScore;
	}
	
	static void getComputermove() {
		int bestScore = -2;
		int candidateScore;
		int move = -1;
		
		for(int i=0; i<9; i++) {
			if(!checkInvalidMove(i)) {
				board[i/3][i%3] = 'O';
				candidateScore = score();
				if(candidateScore>bestScore) {
					bestScore = candidateScore;
					move = i;
				}
				board[i/3][i%3] = ' ';
			}
		}
		board[move/3][move%3] = 'O';
	}
	
	
	private static void runGame() {
		Random random = new Random();
		currentPlayer = (random.nextInt(2)*2)-1;
		boolean gameRunning = true;
		
		setupBoard();
		
		while(gameRunning) {
			switch(currentPlayer) {
			case 1:
				getPlayermove();
				break;
			case -1:
				getComputermove();
				break;
			default:
				break;
			}
			
			if(checkVictory()) {
				gameRunning = false;
				view.printBoard(board);
				view.printVictoryMessage(currentPlayer);
			} else if(checkDraw()) {
				gameRunning = false;
				view.printBoard(board);
				view.printDrawMessage();
			} else {
			currentPlayer *= -1;
			}
		}
		
		if(view.askPlayAgain()) {
			runGame();
		} else {
			view.printGoodBye();
		}
		
	}
	
	public static void main(String[] args) {
		runGame();
	}

}
