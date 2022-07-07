package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: The classes Human, NorBot, and SmartBot are all types of players and share some code. Thus,
 * those classes need a superclass that shares the common code. The class Player is a general game class. 
 * Design: The class should be abstract because there is a common method between all the classes that is not implemented
 * in the same way. However, there are other classes and variables that are shared in the same manner, and thus should
 * have their implementations in the superclass. The common variables and methods that were able to be implemented
 * were implemented, and the one common method that was implemented differently was left as an abstract method.
 */
public abstract class Player {

	public char player;
	public int id;
	final public static char[] PLAYER = {'X', 'O'};
	
	//constructors
	public Player(char p) {
		if(p=='X') 
			id=1;
		if(p=='O') 
			id=2;
		this.player=p;
	}
	public Player() {}
	
	//abstract methods that each player implements differently
	public abstract int boardSelect();
	public abstract int sqSelect();
	
	//normal methods that each player implements in the same way
	public char getPlayer(int i) {
		return PLAYER[i];
	}
	
	public char getPlayer() {
		return player;
	}
	
	public int getId() {
		return id;
	}
}
