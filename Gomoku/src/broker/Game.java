package broker;

/**
 * @author GROUP22
 * created by Steven
 * This class represents the current state of the game being played
 */
public class Game
{
	//playerOne: object<Player>
	private Player playerOne;
	//playerTwo: object<Player>
	private Player playerTwo;
	//round: int
	private int currentRound;
	//board: object<board>
	private Board currentBoard;
	
	//Constructor
	public Game(String gotPlayerOne, String gotPlayerTwo){
		//playerOne: object<Player>
		playerOne = new Player(gotPlayerOne, 'w');
		//playerTwo: object<Player>
		playerTwo = new Player(gotPlayerTwo, 'b');
		newGame();
	}

	//Copy Constructor
	public Game(Game newGame){
		playerOne = new Player(playerOne);
		playerTwo = new Player(playerTwo);
		newGame();
	}
	
	// newgame(): new Board(): void
	public void newGame(){
		currentBoard = new Board();
		currentRound = 0;
	}
		// -- increment round
	public void incrementRound(){
		currentRound += 1;
	}
	
	// makemove(Player): boolean
		// returns true if this move was the winning move
}
