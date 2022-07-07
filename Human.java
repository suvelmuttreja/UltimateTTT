package UltimateTTT;
import java.util.*;
/*
 * Suvel Muttreja CS 2336.003
 * Analysis: This class acts as the real player. The real player only has two actions: select board and select square.
 * Those actions are implemented accordingly through their methods which take user input and return what they chose to game.
 * Design: The class extends first extends Player because a Human is a type of Player and shares common code with other Player types.
 * The specific actions are then implemented. The board select method asks for user input in choosing what board they want to play on, if possible. The method
 * keeps asking until the user enters a valid input, after which it returns that input to the games. Similarly, the 
 * square select method asks for user input in choosing what square they want to play on, which is then returned to the respective game.
 */
public class Human extends Player {

	public Human(char index) {//constructor
		super(index);
	}
	public Human() {super();}//constructor
	
	public int boardSelect() {//asks user for their board choice
		Scanner scan= new Scanner(System.in);
		System.out.println("Please select a valid board!");
		System.out.print("Selected board is: ");
		int boardMove;
		//the method keeps asking until the input is valid
		while(!scan.hasNextInt()) {
			String input=scan.next();
			System.out.print("Input is invalid. ");
			System.out.println("Please select a valid board!");
			System.out.print("Selected board is: ");
		}
		boardMove=scan.nextInt();
		while(!(boardMove==0||boardMove==1||boardMove==2||boardMove==3||boardMove==4||boardMove==5||boardMove==6||boardMove==7||boardMove==8)) {
			System.out.print("Input is invalid. ");
			System.out.println("Please select a valid board!");
			System.out.print("Selected board is: ");
			while(!scan.hasNextInt()) {
					String input=scan.next();
					System.out.print("Input is invalid. ");
					System.out.println("Please select a valid board!");
					System.out.print("Selected board is: ");
			}
			boardMove=scan.nextInt();
		}
		return boardMove;//returns input
	}
	public int sqSelect() {//asks user for their square choice
		Scanner scan=new Scanner(System.in);
		System.out.println("Please select a valid square on the selected board!");
		System.out.print("Selected square is: ");
		int sqMove;
		//the method keeps asking until the input is valid
		while(!scan.hasNextInt()) {
			String input=scan.next();
			System.out.print("Input is invalid. ");
			System.out.println("Please select a valid square on the selected board!");
			System.out.print("Selected square is: ");
		}
		sqMove=scan.nextInt();
		while(!(sqMove==0||sqMove==1||sqMove==2||sqMove==3||sqMove==4||sqMove==5||sqMove==6||sqMove==7||sqMove==8)) {
			System.out.print("Input is invalid. ");
			System.out.println("Please select a valid square on the selected board!");
			System.out.print("Selected square is: ");
			while(!scan.hasNextInt()) {
					String input=scan.next();
					System.out.print("Input is invalid. ");
					System.out.println("Please select a valid square on the selected board!");
					System.out.print("Selected square is: ");
			}
			sqMove=scan.nextInt();
		}
		return sqMove;//returns input
	}
	
	
}
