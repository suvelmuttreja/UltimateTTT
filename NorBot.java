package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: This class represents normal dumb bot. Its moves will be chosen at random. It is a type of player, so it is a player subclass.
 * The bot has two actions, choose board and choose square, both of which are implemented through methods. 
 * Design: The board select method gets a random number from 0-8 and returns it as the board move. The square select does 
 * the same but for square move. A random number method accompanies both methods because that provides the choice. 
 */
public class NorBot extends Player {

	public NorBot(char c) {
		super(c);
	}
	public NorBot() {super();}
	
	public int boardSelect() {//choose board
		System.out.println("Please select a valid board!");
		int boardMove = getRandom();
		System.out.println("Selected board is: "+boardMove);
		return boardMove;
	}
	public int sqSelect() {//choose square
		System.out.println("Please select a valid square on the selected board!");
		int sqMove=getRandom();
		System.out.println("Selected square is: "+sqMove);
		return sqMove;
	}
	
	private int getRandom() {//random from 0 to 8
		return (int)Math.floor(Math.random()*9);
	}
}
