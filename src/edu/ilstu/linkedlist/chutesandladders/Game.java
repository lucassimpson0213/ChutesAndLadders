package edu.ilstu.linkedlist.chutesandladders;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
/**
 * Created on : March 24th, 2023
 *  
 * ULID : lssimp1
 * Class: IT179
 */

/**
 *  this is the game class which runs the whole game and generates players.
 *
 */

public class Game {
	private Square start;
	private ArrayList <Square> positions;
	private int curPlayer;
	



	/**
	 * @param players
	 */
	public Game(int players) {
		positions = new ArrayList<>(players);
		for(int i = 0; i < players; i++) {
			
			Square player = null;
			positions.add(player);
		}
		int jump = calculateJump(1);
		this.start = new Square(1, jump, null);
		
		for(int i = 0; i < players; i++) {
			positions.set(i, start);
		}
		int secondJump = calculateJump(2);
		Square secondSquare = new Square(2, secondJump, start);
		start.nextSquare = secondSquare;
		Square current = secondSquare;
		for(int i = 3; i < 101; i++) {
			int nextJump = calculateJump(i);
			Square next = new Square(i, nextJump, current );
			if(i == 3)
			{
				secondSquare.nextSquare = next;
			}
			current.nextSquare = next;
			current = next;
			
		}
	
	}
	/**
	 * runs the exectution of the game keeps running until a player wins.
	 * @param players
	 * @return whether player has won or not 
	 */
	public boolean play(int players) {
		 curPlayer = 1;
		boolean winner = false;
		while(winner != true) {
			StringBuilder result = new StringBuilder();
			result.append("Player ");
			result.append(curPlayer);
			result.append(" time to roll!");
			System.out.println(result.toString());
			Scanner scan = new Scanner(System.in);
			System.out.println("Press any key to roll the die:");
			scan.nextLine();
			Random rand = new Random();
			
			int curSquare = positions.get(this.curPlayer - 1).currentSquare;
			int move = 0;
			generateSquare(curSquare);
		
			
			if(curSquare < 100 ) {
				 move = rand.nextInt(6 - 1 + 1) + 1;
				while(move > (100 - curSquare)) {
					move = rand.nextInt(6 - 1 + 1) + 1;
				}
				
				if(move != 0) {
					drawJump(move);
					move(curPlayer,move);
					curSquare = positions.get(this.curPlayer - 1).currentSquare;
					generateSquare(curSquare);
				}
				else {
					System.out.println("Player did not move");
				}
				
				
			}
			
			
			int jumpVal = positions.get(curPlayer - 1).jumpVal;
			
			
			if( jumpVal > 0 && curSquare != 100) {
				curSquare = positions.get(this.curPlayer - 1).currentSquare;
				move(curPlayer, jumpVal);
				curSquare = positions.get(this.curPlayer - 1).currentSquare;
				generateLadder(jumpVal, this.curPlayer);
				generateSquare(curSquare);
				
				
			}
			else if( jumpVal < 0 && curSquare != 100) {
				move(curPlayer, positions.get(curPlayer - 1).jumpVal);
				generateChute(jumpVal, this.curPlayer);
				curSquare = positions.get(this.curPlayer - 1).currentSquare;
				generateSquare(curSquare);
			
			}
			
			if(curSquare == 100) {
				winner = true;
				System.out.println("Player " + curPlayer + " Won!");
			}
			
			
			if(curPlayer >= players) {
				curPlayer = 1;
			}
			else {
				curPlayer++;
			}
			
		}
		return winner;
	}
	
	/**
	 * moves player to designated square
	 * @param playerNum
	 * @param jumps
	 * @return  current square
	 */
	public int move(int playerNum, int jumps) {
		
		int currentPosition = 0;
		if(jumps > 0){
			for(int i = 0; i < jumps; i++) {
				positions.set(playerNum-1, positions.get(playerNum - 1).nextSquare);
				
				currentPosition = positions.get(playerNum - 1).currentSquare;
			}
		}
		else if(jumps < 0) {
			int posJumps = Math.abs(jumps);
			for(int i = 0; i < posJumps ; i++) {
				positions.set(playerNum-1, positions.get(playerNum - 1).prevSquare);
				currentPosition = positions.get(playerNum - 1).currentSquare;
			}
			
			
				
		}
		return currentPosition;
	
		
		
	}
	
	/**
	 * calculates the sign.
	 * @return the sign of the number
	 */
	private int calcSign() {
		Random rand = new Random();
		int randNum = rand.nextInt(2);
		int sign = -1;
		 if(randNum == 1) {
			sign = 1;
		}
		 return sign;
	}
	
	/**
	 * generates a percentage 
	 * @return percentage
	 */
	private int generatePercentage() {
		Random rand = new Random();
		return rand.nextInt(101);
	}
	
	/**
	 * calculates the jump value
	 * @param curSquare
	 * @return current square number
	 */
	public int calculateJump(int curSquare){
		int number = generatePercentage();
		int num = 0;
		if(number >= 75) {
			Random rand = new Random();
			num = rand.nextInt(101);
			int sign = calcSign();
			if(sign > 0) {
				while(num >= (100 - curSquare)) {
				
					num = rand.nextInt(101);
				
				}
			}
			else if(sign < 0) {
				
				while(num >= curSquare)
				{
					num = rand.nextInt(101);
				}
				num *= -1;
			}
			
			
		}
		
		return num;
	}
	
	/**
	 * 
	 */
	
	
	/**
	 * @param square
	 */
	private void generateSquare(int square) {
		System.out.println(" --------- ");
		System.out.println("|         |");
		System.out.println("|         |");
		System.out.println("|         |" + "  " + square);
		System.out.println("|         |");
		System.out.println(" --------- ");
	}
	
	/**
	 * @param jumps
	 * @param playerNum
	 */
	private void generateLadder(int jumps, int playerNum) {
		System.out.println("   |---|");
		System.out.println("   |---|" + " Ladder!");
		System.out.println("   |---|");
		System.out.println("   |---|" + " Player " + playerNum + " moved up " + jumps + " spaces!");
		System.out.println("   |---|");
		System.out.println("   |---|");
		System.out.println("   |---|");
		System.out.println("   |---|");
		
	}
	/**
	 * @param jumps
	 * @param playerNum
	 */
	private void generateChute(int jumps, int playerNum) {
		System.out.println("   |+++|");
		System.out.println("   |+++|" + " Chute!");
		System.out.println("   |+++|");
		System.out.println("   |+++|" + " Player " + playerNum + " moved down " + jumps + " spaces!");
		System.out.println("   |+++|");
		System.out.println("   |+++|");
		System.out.println("   |+++|");
		System.out.println("   |+++|");
	}
	
	/**
	 * @param jumps
	 */
	private void drawJump(int jumps)
	{
		System.out.println("     |");
		System.out.println("     |");
		System.out.println("     |");
		System.out.println("Player Moved " + jumps + " spaces! ");
		System.out.println("     |");
		System.out.println("     |");
		System.out.println("     |");
		System.out.println("     V");
	}
	

	
	
	
	/**
	 * static class that represents a node of the doubly linked list.
	 * @author lucassimpson33
	 *
	 */
	private static class Square {
		Square prevSquare;
		Square nextSquare;
		int currentSquare;
		int jumpVal;
		//o	A constructor that will take the square number, the jump value, and the previous square reference.
		public Square(int currentSquare, int jumpVal, Square prevSquare ) {
			this.currentSquare = currentSquare; 
			this.jumpVal = jumpVal;
			this.prevSquare = prevSquare;
		}
		
		
		

	}
}
