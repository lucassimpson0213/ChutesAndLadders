package edu.ilstu.linkedlist.chutesandladders;
import java.util.Scanner;
/**
 * Created on : march 24th, 2023
 *  
 * ULID : lssimp1
 * Class: IT179
 */

/**
 * 
 * 
 * main class that runs the whole program.
 * @author lucassimpson33
 *
 */

public class main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println(" WELCOME TO SHOOTS AND LADDERS!");
		System.out.println("********************************");
		System.out.println("How many Players? ");
		String throwaway;
		
		    while (!scan.hasNextInt()) {
		        System.out.println("Please enter a number: ");
		        throwaway = scan.nextLine();
		    }
		    int players = scan.nextInt();
		    
		
		Game game = new Game(players);
		game.play(players);
		
	}

}
