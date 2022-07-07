package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: The classes TwoHumans, TwoBots, and OneEach are all types of games and share some code. Thus,
 * those classes need a superclass that shares the common code. The class GenGame is a general game class. 
 * Design: The class should be abstract because there is a common method between all the classes that is not implemented
 * in the same way. However, there are other classes and variables that are shared in the same manner, and thus should
 * have their implementations in the superclass. The common variables and methods that were able to be implemented
 * were implemented, and the one common method that was implemented differently was left as an abstract method.
 */

public abstract class GenGame {//generalized game class

	public static boolean allOpen=true;//keeps check if all boards are open for play or not
	public int id=-1;//keeps track of id of player
	public int boardMove=-1;//keeps track of which board is chosen
	public int sqMove=-1;//keeps track of which square is chosen
	public int loop=-1;//keeps track of turn
	
	public GenGame() {}
	
	public abstract void playGame();//method that is shared but implemented differently because all subclasses play differently
	
	//normal methods that all game types implement the same way
	public int getBoard() {//see which board player chose
		return boardMove;
	}
	
	
	public void update() {//update all variables at end of turn
		boardMove=sqMove;
		id = 1 - id;
		loop++;
	}

	public static boolean openBoard() {//check if all boards are open for player
		return allOpen;
	}
}
