import java.util.Random;
import java.util.Scanner;

/**
 * CPSC 219
 * GROUP 22
 * Team Assignment 1: Guessing Game
 * 
 * @author Aahil
 * @author Emily
 * @author Emmanuel
 * @author Leslie
 * @author Steven
 * 
 * @version 2.0
 */
public class GuessingGame {
	
	/**
	 * This Method will continue to prompt user to guess
	 * the number until they run out of tries or guess it right.
	 * If the user enters an invalid guess the user will be prompted
	 * to enter a number within the valid range
	 * @param numberToGuess
	 * @return
	 */
	public static boolean getAndCheckGuess(int numberToGuess) {
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter a guess: ");
		int guess = reader.nextInt();
		
		if (guess>numberToGuess && guess < 21) {
			System.out.println("Too high");
		} else if (guess<numberToGuess && guess > 1) {			
			System.out.println("Too low");
		} else if (guess==numberToGuess) {
			System.out.println("You guessed it");
			return true;
		} else {
			System.out.print("Guess is not valid");
		}

		return false;
	}
	
	/**
	 * This is the main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		int numToGuess = 0;
		boolean isValid = false;
		
		for(int counter = 0; counter<5 && !isValid; counter++) {
			isValid = getAndCheckGuess(numToGuess);
		}
		
		if(isValid==true) {
			System.out.print("Well done!");
		} else {
			System.out.print("The number to guess was "+numToGuess);
		}
		
		if (args.length < 1) {
			Random rand = new Random();
			// nextInt will return a number between 0 and 20 (exclusive 20).  Adding 1 results in 
			// a number between 1 and 20 (inclusive).
			numToGuess = rand.nextInt(20) + 1;
		} else {
			// get the number provided as a command line argument and use it as the number to 
			// guess for the game.
			numToGuess = Integer.parseInt(args[0]);
		}
		boolean correct = getAndCheckGuess(numToGuess);
		System.out.println(correct);
	}	
}