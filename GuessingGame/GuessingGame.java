import java.util.Random;
import java.util.Scanner;

/**
 * CPSC 219 GROUP 22 Team Assignment 1: Guessing Game
 * 
 * @author Aahil
 * @author Emily
 * @author Emmanuel
 * @author Leslie
 * @author Steven
 * 
 * @version 2.0
 */
public class GuessingGame
{

	/**
	 * This Method will continue to prompt user to guess the number until they
	 * run out of tries or guess it right. If the user enters an invalid guess
	 * the user will be prompted to enter a number within the valid range
	 * 
	 * @param numberToGuess as int
	 * @return true if user correctly guesses the number, otherwise returns false
	 */
	public static boolean getAndCheckGuess(int numberToGuess)
	{
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter a guess: ");
		int guess = reader.nextInt();
		boolean isCorrect = false;

		while (guess < 1 || guess > 20)
		{
			System.out.println("Guess is not valid");
			System.out.print("Enter a guess: ");
			guess = reader.nextInt();
		}

		if (guess > numberToGuess)
		{
			System.out.println("Too high");
		} else if (guess < numberToGuess)
		{
			System.out.println("Too low");
		} else
		{
			System.out.println("You guessed it");
			isCorrect = true;
		}

		return isCorrect;
	}

	/**
	 * This is the main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// initialize variables
		Random rand = new Random();
		boolean isCorrect = false;
		int numToGuess;
		
		System.out.println("you should see this change on git hub after i commit and push.");
		//test again
		
		//Check args
		if (args.length < 1)
		{
			// nextInt will return a number between 0 and 20 (exclusive 20).
			// Adding 1 results in
			// a number between 1 and 20 (inclusive).
			numToGuess = rand.nextInt(20) + 1;
		} else
		{
			// get the number provided as a command line argument and use it as
			// the number to
			// guess for the game.
			numToGuess = Integer.parseInt(args[0]);
		}
		
		//begin game; for loop to 5 tries
		for (int counter = 0; counter < 5 && !isCorrect; counter++)
		{
			isCorrect = getAndCheckGuess(numToGuess);
		}
		
		if (isCorrect)
		{
			System.out.println("Well done!");
		} else
		{
			System.out.println("The number to guess was " + numToGuess);
		}
		
		System.out.println(isCorrect);
	}
}
