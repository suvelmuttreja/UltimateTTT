package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: This game is bot v. bot. It will use the robot object as its player, which will make the choices and an ultgame
 * object for the game. Since this a type of game, it will be a subclass of gengame. The playgame method will start the game
 * and implement the logic to play the game, using the objects accordingly.
 * Design: This class implements its own version of playgame method inherited from superclass. To start, it checks if there is
 * a winner or tie and stops playing if there is. Then, if needed, the board move is chosen. Then, a square move is chosen. Then,
 * the game object plays that move. However, if that move is invalid, this method asks for a new board and/or square move until the 
 * move is valid. After that, the parameters update and the next turn starts. 
 *  */
public class TwoBots extends GenGame {

	public TwoBots() {
		super();
	}
	
	public void playGame() {
		id = 0;
		UltGame game = new UltGame();
		NorBot rob=new NorBot();
		game.print();
		loop = 0;

		System.out.println("Current player is: "+rob.getPlayer(id));
		game.printMoves(boardMove);
		boardMove=rob.boardSelect();
		
		while (true) {
			//check if there is winner and stop playing
			if(game.isWin()==1) {
				System.out.println("Game winner is: X!");
				break;
			}
			if(game.isWin()==2) {
				System.out.println("Game winner is: O!");
				break;
			}
			if(game.isTie()) {
				System.out.println("It's a tie");
				break;
			}
			if(loop!=0) 
				System.out.println("Current player is: "+rob.getPlayer(id));
			
			boolean result=false;
			allOpen=false;
			
			while(game.isFull(boardMove)) {//choose new board if previous board is full
				System.out.println("Board "+boardMove+ " is full!");
				allOpen=true;
				game.printMoves(boardMove);
				boardMove=rob.boardSelect();
			}
			
			if(loop==0)
				game.printMoves(boardMove);
			if(!allOpen||loop!=0) {
				System.out.println("Selected board is: "+boardMove); 
				game.printMoves(boardMove);
			}
			sqMove=rob.sqSelect();
			result = game.doMove(boardMove, sqMove, id+1);

			// Process invalid case.
			while (!result) {
				System.out.println("Player "+rob.getPlayer(id)+" made an invalid move on turn "+loop+". Please try again!");
				System.out.println("Current player is: "+rob.getPlayer(id));
				System.out.println("Selected board is: "+boardMove);
				game.printMoves(boardMove);
				if(allOpen) {
					boardMove=rob.boardSelect();
				}
				sqMove=rob.sqSelect();
				game.print();
				result = game.doMove(boardMove, sqMove, id+1);
				if(result) {
					allOpen=false;
					break;
				}
			}
			game.print();

			// Update for next move.
			update();
		}
	}
}
