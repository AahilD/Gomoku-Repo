import java.util.Random;
import java.util.Scanner;


public class GuessingGame {
	
	public static boolean getAndCheckGuess(int numberToGuess) {
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
