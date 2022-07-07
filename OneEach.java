package UltimateTTT;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: This game is human v. bot. Since it can be human vs dumb or smart bot, there are two methods implemented.
 * Design: The constructor first gets type of game to be played. The playgame method then starts the respective game. 
 * If it is vs dumb, then the method does human vs normal bot. Otherwise, it creates the smart bot for human. Two methods
 * need to be implemented because if statements to not initialize a variable. Throughout the method, the loop counter is 
 * used to check whose turn it is, with the respective selection methods called. 
 */
public class OneEach extends GenGame {
	public int type=0;
	public OneEach() {super();}
	public OneEach(int mixType) {//constructor
		this.type=mixType;
	}
	
	public void playGame() {//choose bot type
		if(type==1) playDumb();
		else if(type==2) playSmart();
	}
	
	public void playDumb() {
		id = 0;
		UltGame game = new UltGame();
		Human hum=new Human('X');
		NorBot rob= new NorBot('O');
		game.print();
		loop = 0;
		boardMove=0;
		sqMove=0;
		char player=hum.getPlayer();
		
		System.out.println("Current player is: "+player);
		game.printMoves(boardMove);
		boardMove=hum.boardSelect();
		
		while (true) {

			//check if game ends
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
			//set player for current loop
			if(loop%2==0) 
				player=hum.getPlayer();
			else if(loop%2==1) 
				player=rob.getPlayer();
			if(loop!=0) {
				System.out.println("Current player is: "+player);
			}
			
			boolean result=false;
			allOpen=false;
			
			while(game.isFull(boardMove)) {//choose new board is previous board is full
				System.out.println("Board "+boardMove+ " is full!");
				allOpen=true;
				game.printMoves(boardMove);
				if(loop%2==0) {
					boardMove = hum.boardSelect();
				}
				else {boardMove = rob.boardSelect();
				}
			}
			
			if(loop==0)
				game.printMoves(boardMove);
			if(!allOpen||loop!=0) {
				System.out.println("Selected board is: "+boardMove);
				game.printMoves(boardMove);
			}
			if(loop%2==0) {//choose square
				sqMove=hum.sqSelect();
			}
			else {
				sqMove=rob.sqSelect();
			}
			result = game.doMove(boardMove, sqMove, id+1);

			// Process invalid case.
			while (!result) {
				System.out.println("Player "+player+" made an invalid move on turn "+loop+". Please try again!");
				System.out.println("Current player is: "+player);
				System.out.println("Selected board is: "+boardMove);
				game.printMoves(boardMove);
				if(allOpen) {
					if(loop%2==0) {
						boardMove=hum.boardSelect();
					}
					else {
						boardMove = rob.boardSelect();
					}
				}
				if(loop%2==0) {
					sqMove=hum.sqSelect();
				}
				else {
					sqMove=rob.sqSelect();
				}
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
	
	public void playSmart() {
		id = 0;
		UltGame game = new UltGame();
		Human hum=new Human('X');
		SmartBot rob= new SmartBot('O');
		game.print();
		loop = 0;
		boardMove=0;
		sqMove=0;
		char player=hum.getPlayer();

		System.out.println("Current player is: "+player);
		game.printMoves(boardMove);
		boardMove=hum.boardSelect();
		
		while (true) {

			//check if game ends
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
			//set player for current loop
			if(loop%2==0) 
				player=hum.getPlayer();
			else if(loop%2==1) 
				player=rob.getPlayer();
			if(loop!=0) {
				System.out.println("Current player is: "+player);
			}
			
			boolean result=false;
			allOpen=false;
			
			while(game.isFull(boardMove)) {//choose new board is previous board is full
				System.out.println("Board "+boardMove+ " is full!");
				allOpen=true;
				game.printMoves(boardMove);
				if(loop%2==0) {
					boardMove = hum.boardSelect();
				}
				else {
					boardMove = rob.boardSelect(game.getResult());
				}
			}
			
			if(loop==0)
				game.printMoves(boardMove);
			else if(!allOpen||loop!=0) {
				System.out.println("Selected board is: "+boardMove);
				game.printMoves(boardMove);
			}
			if(loop%2==0) {//choose square
				sqMove=hum.sqSelect();
			}
			else {
				sqMove=rob.sqSelect(game.getBoard(boardMove));
			}
			result = game.doMove(boardMove, sqMove, id+1);

			// Process invalid case.
			while (!result) {
				System.out.println("Player "+player+" made an invalid move on turn "+loop+". Please try again!");
				System.out.println("Current player is: "+player);
				System.out.println("Selected board is: "+boardMove);
				game.printMoves(boardMove);
				if(allOpen) {
					if(loop%2==0) {
						boardMove=hum.boardSelect();
					}
					else {
						boardMove = rob.boardSelect(game.getResult());
					}
				}
				if(loop%2==0) {
					sqMove=hum.sqSelect();
				}
				else {
					sqMove=rob.sqSelect(game.getBoard(boardMove));
				}
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
