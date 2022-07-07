package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: The SmartBot class is similar to NorBot, but this class chooses the best move to make on each turn.
 * Even though this is a type of Player, the abstract class implementations will be dud methods because they do not fit 
 * the logic of the code. This class also has an algorithm to choose the best move instead of a random number method.  
 * Design: This class extends Player because most methods in the superclass are useful, and the established hierarchy follows OOP.
 * However, the selection methods will be implemented differently because these need access to the state of the board 
 * in order to make a selection. The selection methods will take the state of board as input and output the move choice. 
 * Both methods will call the same minimax algorithm to make their choice. Since the algorithm needs to check which 
 * potential moves result in a win, a private iswin methods is needed, as the ultgame version cannot be accessed without 
 * static change conflicts. 
 */
public class SmartBot extends Player {

	public SmartBot(char c) {
		super(c);
	}
	public SmartBot() {
		super();
	}
	
	public int boardSelect(int[] board) {
		System.out.println("Please select a valid board!");
		int boardMove = bestBoard(board);
		System.out.println("Selected board is: "+boardMove);
		return boardMove;
	}
	public int sqSelect(char[] board) {
		System.out.println("Please select a valid square on the selected board!");
		int sqMove=bestSquare(board);
		System.out.println("Selected square is: "+sqMove);
		return sqMove;
	}
	//these are here because they need to be implemented to access the other player methods and establish a good hierarchy
	public int boardSelect() {return -1;}
	public int sqSelect() {return -1;}
	
	private int bestBoard(int[] board) {//chooses best board to play
		//this method is different because board array has tie value as well that needs to be accounted for
		int bestScore=Integer.MIN_VALUE;
		int move=0;
		int prev=0;
		for(int i=0; i<9; i++) {//choose best move
			if(board[i]==0) {
				board[i]=2;
				int score=minimax(board, 0, false);
				if(score==3) {//if new score = 3, go back to previous score and forget this as a choice
					if(i==0) 
						bestScore=Integer.MIN_VALUE;
					else {
						board[i-prev]=2;
						score=minimax(board, 0,false);
						board[i-prev]=0;
					}
				}
				board[i]=0;
				if(score>bestScore) {
					bestScore=score;
					move=i;
				}
				prev++;
			} 
		}
		return move;
	}
	
	private int bestSquare(char[] board) {//chooses best square to play
		//since individual games do not have tie values, that condition does not need to be accounted for
		int bestScore=Integer.MIN_VALUE;
		int move=0;
		int[]newBoard=new int[9];
		for(int i=0; i<9; i++) {
			//change board from chars to ints for better analysis in minimax
			if(board[i]=='X') 
				newBoard[i]=1;
			if(board[i]=='O') 
				newBoard[i]=2;
			if(Character.isDigit(board[i])||board[i]=='*') 
				newBoard[i]=0;
		}
		for(int i=0; i<9; i++) {//choose best move
			if(UltGame.openSq(board,i)) {
				newBoard[i]=2;
				int score=minimax(newBoard, 0, false);
				newBoard[i]=0;
				if(score>bestScore) {
					bestScore=score;
					move=i;
				}
			}
		}
		return move;
	}
	
	private int minimax(int[] board, int depth, boolean isMax) {
		//algorithm that returns a score of each possible potential move
		//the above methods then evaluate the scores and play best move
		if(depth==0){
			//change board values for better evaluation. for minimax, opposite moves need same score value in different signs
				for(int i=0; i<9; i++) {
				if(board[i]==1) //human score
					board[i]=-1;
				if(board[i]==2) //bot score
					board[i]=1;
			}
		}
		int win=this.isWin(board);//get moveset that results in win
		if(win!=0) 
			return win;//base case
		if(isMax) {//get highest possible score for potential bot moves
			int bestScore=Integer.MIN_VALUE;
			for(int i=0; i<9; i++) {
				if(board[i]==0) {
					board[i]=1;
					int score=minimax(board,depth+1,false);//recursive call to check whole moveset
					board[i]=0;
					if(score!=3&&score>bestScore) {
						bestScore=score;//choose max value
					}
				}
			}
			return bestScore;
		}
		else {//get lowest possible score for potential human moves
			int bestScore=Integer.MAX_VALUE;
			for(int i=0; i<9; i++) {
				if(board[i]==0) {
					board[i]=-1;
					int score=minimax(board,depth+1,true);//recursive call to check whole moveset
					board[i]=0;
					if(score!=3&&score<bestScore) {
						bestScore=score;//choose mini value
					}
				}
			}
			return bestScore;
		}
	}
	
	private int isWin(int[] board) {
		//helper method to check if board state has a winner
		for(int i=0; i<3; i++) {
			if(board[3*i]!=0&&board[3*i]!=3&&board[3*i]==board[3*i+1]&&board[3*i+1]==board[3*i+2])
				return board[3*i];
			if(board[i]!=0&&board[i]!=3&&board[i]==board[i+3]&&board[i+3]==board[i+6])
				return board[i];
		}
		if(board[0]!=0&&board[0]!=3&&board[0]==board[4]&&board[4]==board[8])
			return board[0];
		if(board[6]!=0&&board[6]!=3&&board[6]==board[4]&&board[4]==board[2])
			return board[6];
		return -1;
	}

}
