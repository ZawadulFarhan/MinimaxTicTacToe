package TicTacToeInterface;

import java.util.Scanner;

public class TicTacToeCLI implements TicTacToeInterface{
	Scanner scan;
	
	public TicTacToeCLI() {
		scan = new Scanner(System.in);
	}
	
	@Override
	public void printBoard(char[][] board) {
		System.out.println();
		System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
		System.out.println("-+-+-");
		System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println("-+-+-");
		System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
		System.out.println();
	}

	@Override
	public void printInvalidMoveMessage() {
		System.out.println("Your input is invalid. Please try again");
	}

	@Override
	public int promptPlayerMove() {
		System.out.println("Please enter a move(1-9)\n");
		int n = (scan.nextInt()-1);
		scan.nextLine();
		return n;
	}

	@Override
	public void printVictoryMessage(int currentPlayer) {
		
		switch(currentPlayer) {
		case 1:
			System.out.println("Congratulations! You have won!\n");
			break;
		case -1:
			System.out.println("Computer has won... Better luck next time\n");
			break;
		default:
			break;
		}
	}

	@Override
	public void printDrawMessage() {
		System.out.println("Game has ended in a draw...How uneventful...\n");
	}

	@Override
	public void printGoodBye() {
		System.out.println("Thank you for playing tictactoe. Hope to see you again!");
	}

	@Override
	public boolean askPlayAgain() {
		System.out.println("Do you want to play again?(y/n)");
		
		switch(scan.nextLine().toLowerCase().charAt(0)) {
		 case 'y':
			 return true;
		 default:
			 return false;
		}
	}
}
