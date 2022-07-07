package UltimateTTT;
import java.util.*;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: This class starts the program. The main method asks what game the user wants to play through a series of questions, 
 * then starts the respective game.
 * Design: The main method first asks if the game is human v. human, bot v. bot, or human v. bot. If, the user selects human v. bot,
 * the method asks if they want to play against a dumb bot or smart ai that has minimax implementation. The method then starts the
 * respective game. Throughout the questioning, the method makes sure that the input is valid. 
 */
public class MainGame {
	public static void main(String[] args) {

		System.out.println("===== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! =====");
		System.out.println("What mode would you like to play in?");
		System.out.print("Type 1 if both players are human, type 2 if both players are bots, and type 3 if one player is human and the other player is a bot: ");
		Scanner scan = new Scanner(System.in);
		int gameType;
		//checks if game type input is valid
		while(!scan.hasNextInt()) {
			String input=scan.next();
			System.out.println("Input is invalid. Please enter a valid input.");
			System.out.print("Type 1 if both players are human, type 2 if both players are bots, and type 3 if one player is human and the other player is a bot: ");
		}
		gameType=scan.nextInt();
		while(!(gameType==3||gameType==1||gameType==2)) {
				System.out.println("Input is invalid. Please enter a valid input.");
				System.out.print("Type 1 if both players are human, type 2 if both players are bots, and type 3 if one player is human and the other player is a bot: ");
				while(!scan.hasNextInt()) {
					String input=scan.next();
					System.out.println("Input is invalid. Please enter a valid input.");
					System.out.print("Type 1 if both players are human, type 2 if both players are bots, and type 3 if one player is human and the other player is a bot: ");
				}
			gameType=scan.nextInt();
		}
		if(gameType==1) 
			humanPlayers();
		else if(gameType==2) 
			robotPlayers();
		else if(gameType==3) {
			System.out.print("Type 1 if you want to play against a normal bot, and type 2 if you want to play against smart AI: ");
			int mixType;
			//checks if secondary game type input is valid
			while(!scan.hasNextInt()) {
				String input=scan.next();
				System.out.println("Input is invalid. Please enter a valid input.");
				System.out.print("Type 1 if you want to play against a normal bot, and type 2 if you want to play against smart AI: ");
			}
			mixType=scan.nextInt();
			while(!(mixType==3||mixType==1||mixType==2)) {
					System.out.println("Input is invalid. Please enter a valid input.");
					System.out.print("Type 1 if both players are human, type 2 if both players are bots, and type 3 if one player is human and the other player is a bot: ");
					while(!scan.hasNextInt()) {
						String input=scan.next();
						System.out.println("Input is invalid. Please enter a valid input.");
						System.out.print("Type 1 if you want to play against a normal bot, and type 2 if you want to play against smart AI: ");
					}
				mixType=scan.nextInt();
			}
			if(mixType==1)
				mixPlayers(1);
			else if(mixType==2)
				mixPlayers(2);
		}
	}

	public static void humanPlayers() {//starts human v. human
		TwoHumans bothReal = new TwoHumans();
		bothReal.playGame();

		// If we get here, game worked.
	}
	
	public static void robotPlayers() {//starts bot v. bot
		TwoBots ai=new TwoBots();
		ai.playGame();
		// If we get here, game worked.
	}
	
	public static void mixPlayers(int mixType) {//starts human v. bot with appropriate specification
		
		OneEach mix=new OneEach(mixType);
		mix.playGame();

		// If we get here, game worked.
	}
}
