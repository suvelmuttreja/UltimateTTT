package UltimateTTT;
import java.util.*;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: This class acts as the board for the whole game. It has a variety of methods to access board information, and
 * a variety of methods to change board information based on player input. 
 * Design: The class holds the whole board, a smaller board holding the results of the 9 games, and a parameter holding the 
 * previous board move. The constructor initializes those parameters. The getter methods allow the parameters to be accessed
 * so that other classes can also analyze the board state. The main print method prints the board for user, and a helper 
 * method prints if any games have winners. A printmoves method prints all legal moves currently available. A doMove method
 * uses a helper method to check the validity of input move and moves if it is valid. A helper isvalid method checks if 
 * given move is legal. The iswin and istie methods check if the results board has a winner or tie. The isfull methods
 * check if a given small board is full. The opensq method checks if the given square is open, the getoutcome method 
 * returns if the small game has a winner or tie using a helper method, so that the result board can be updated. 
 * The helper winner method checks if three given characters result in a win on the small game. 
 *  */
public class UltGame {

	final private static int CATS = 3;

	private char[][] board;//1 = player 1, 2 = player 2, 0 = unfilled.
	private int prevPos;
	private int[] result;//1 = player 1, 2 = player 2, 0 = unfilled, 3 = tie
	
	public UltGame() {

		// 1st index is board, 2nd is individual square.
		board = new char[9][9];
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				board[i][j]=Character.forDigit(j,10);//empty board
		prevPos = -1;
		result = new int[9];
	}
	
	public int[] getResult() {
		return result;
	}
	
	public char[] getBoard(int i) {
		return board[i];
	}
	
	public void print() {//print whole board
		for(int i=0; i<3; i++) {
			int a=3*i;
			int b=3*i+1;
			int c=3*i+2;
			for(int j=0; j<3; j++) {
				System.out.print("Board#"+a+" | "+board[a][3*j]+" | "+board[a][3*j+1]+" | "+board[a][3*j+2]+" | ");
				System.out.print("Board#"+b+" | "+board[b][3*j]+" | "+board[b][3*j+1]+" | "+board[b][3*j+2]+" | ");
				System.out.println("Board#"+c+" | "+board[c][3*j]+" | "+board[c][3*j+1]+" | "+board[c][3*j+2]+" | ");
			}
		}
		
		printWinners();
	}
	
	public void printWinners() {//check and print if board has winner
		for (int i=0; i<result.length; i++) {
			if(result[i]>0) {
				if(result[i]==1) 
					System.out.println("Board#"+i+" winner is Player X");
				else if(result[i]==2) 
					System.out.println("Board#"+i+" winner is Player O");
			}
			if(isFull(i)&&result[i]!=1&&result[i]!=2) 
				System.out.println("Board#"+i+" is a tie");
		}
	}
	
	public void printMoves(int pos) {//check and print all possible legal moves
		if(GenGame.openBoard()) {//if previous board is full, board and square moves are allowed
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					if(openSq(board[i],j)) {
						System.out.println("A possible legal move is: Board#"+i+" and Sqaure#"+j);
					}
				}
			}
		}
		else if(pos>-1){
			for(int j=0; j<9; j++) {
				if(openSq(board[pos],j)) {
					System.out.println("A possible legal move on Board#"+pos+" is: Sqaure#"+j);
				}
			}
		}
	}
	
	public boolean doMove(int game, int pos, int turn) {

		// Check validity then move.
		if (isValid(game, pos)) {
			if(turn==1) 
				board[game][pos] = 'X';
			else if(turn==2) 
				board[game][pos] = 'O';
			prevPos = pos;
			if(result[game]==0) //game outcome cannot be changed after someone has won the board
				result[game]  = getOutcome(board[game], turn);
			if(result[game]==1||result[game]==2) //if game has winner, board style changes
				for(int i=0; i<9; i++) 
					if(Character.isDigit(board[game][i])) 
						board[game][i]='*';
			return true;
		}

		// invalid move
		return false;
	}
	
	public boolean isValid(int game, int pos) {//checks validity of given move

		// First move can be anywhere.
		if (prevPos == -1) {
			return true;
		}
		//move can be anywhere if previous board is full
		if(GenGame.openBoard()) {
			if (result[game] == CATS) return false; 
			if (!openSq(board[game], pos)) return false;
			return true;
		}
		// See if forced game is still open.
		else if (result[prevPos] !=CATS) {

			// You must play in this game on an open square.
			if (result[game] == CATS) return false;
			if (prevPos != game)  return false;
			if (!openSq(board[game], pos)) return false;
			return true;
		}
		// Otherwise, any open square in any unfinished game is fine.
		else {
			if (result[game] == CATS) return false; 
			if (!openSq(board[game], pos)) return false;
			return true;
		}
	}
	
	public int isWin() {//checks if big 9x9 board has a winner
		for(int i=0; i<3; i++) {
			if(result[3*i]!=0&&result[3*i]!=3&&result[3*i]==result[3*i+1]&&result[3*i+1]==result[3*i+2])//check rows
				return result[3*i];
			if(result[i]!=0&&result[i]!=3&&result[i]==result[i+3]&&result[i+3]==result[i+6])//check columns
				return result[i];
		}
		//check diagonals
		if(result[0]!=0&&result[0]!=3&&result[0]==result[4]&&result[4]==result[8])
			return result[0];
		if(result[6]!=0&&result[6]!=3&&result[6]==result[4]&&result[4]==result[2])
			return result[6];
		return -1;
	}
	
	public boolean isTie() {//checks if big board ends in a tie
		for(int i=0; i<result.length; i++) {
			if(result[i]==0) 
				return false;
		}
		return isWin()==-1;
	}
	
	public boolean isFull(int i) {//checks if small board is full
		for(int j=0; j<board[i].length; j++)
			if(Character.isDigit(board[i][j])||board[i][j]=='*') 
				return false;
		return true;
	}
	public static boolean isFull(char[] board) {//checks if small board is full
		for(int i=0; i<board.length; i++)
			if(Character.isDigit(board[i])||board[i]=='*') 
				return false;
		return true;
	}

	// Returns true if pos in the single game game is open.
	public static boolean openSq(char[] game, int pos) {
		return Character.isDigit(game[pos])||game[pos]=='*';
	}
	
	public static int getOutcome(char[] game, int turn) {//checks if small board has a winner

		// Check rows.
		for (int i=0; i<3; i++) {
			int check = winner(game[3*i], game[3*i+1], game[3*i+2], turn);
			if (check != 0) 
				return check;
		}

		// Check cols.
		for (int i=0; i<3; i++) {
			int check = winner(game[i], game[i+3], game[i+6], turn);
			if (check != 0) 
				return check;
		}

		// Check diag.
		int check = winner(game[0], game[4], game[8], turn);
		if (check != 0) 
			return check;
		check = winner(game[2], game[4], game[6], turn);
		if (check != 0) 
			return check;

		// Look for open square, since no one has won yet.
		for (int i=0; i<9; i++)
			if (Character.isDigit(game[i]))
				return 0;

		// It's a tie game if reach here.
		if(isFull(game))
			return CATS;
		return CATS;
	}
	
	//checks if given chars make a winning combo
	public static int winner(char a, char b, char c, int turn) {

		// Add in non-cats game squares.
		ArrayList<Character> list = new ArrayList<Character>();
		if (a != Character.forDigit(CATS, 10)) 
			list.add(a);
		if (b != Character.forDigit(CATS, 10)) 
			list.add(b);
		if (c != Character.forDigit(CATS, 10)) 
			list.add(c);

		// Brute force check of all outcomes.
		for(int i=0; i<9; i++)
			if (list.contains(Character.forDigit(i, 10))) 
				return 0;
		if (list.size() == 0) 
			return turn;
		if (list.size() == 1) 
			return list.get(0);

		// Two squares by players - need to check if they're the same.
		if (list.size() == 2) {
			if (list.get(0) == list.get(1)) 
				return 0;
		}

		// All three squares are filled in. If all same, that's the result, else 0.
		if (list.get(0) == list.get(1) && list.get(1) == list.get(2)) {
			if(list.get(0)=='X') 
				return 1;
			else if(list.get(0)=='O') 
				return 2;
		}
		return 0;
	}
}
