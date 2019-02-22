package broker;

/**
 * @author GROUP22
 *TESTESTESTESTESTESTES
 * This class represents the current state of the game being played
 */
public class Game
{
	//Constuctors
	
	public Game(String gotPlayerOne, String gotPlayerTwo){
		//playerOne: object<Player>
		Player playerOne = new Player(gotPlayerOne, 'w');
		//playerTwo: object<Player>
		Player playerTwo = new Player(gotPlayerTwo, 'b');
	}

	//board: object<board>
	public Board currentBoard = new Board();
	
	//round: int
	public int currentRound = 0;

	// newgame(): new Board(): void
		// -- increment round
	public void newGame(){
		currentBoard = new Board();
	}
	public void incrementRound(){
		currentRound += 0;
	}
	
	public void getRound(){
		
	}
	
	// makemove(Player): boolean
		// returns true if this move was the winning move
}
