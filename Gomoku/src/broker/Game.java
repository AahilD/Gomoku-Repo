package broker;

/**
 * @author GROUP22
 *
 * This class represents the current state of the game being played
 */
public class Game
{
	//playerOne: object<Player>
	public Player playerOne = new Player();
	
	//playerTwo: object<Player>
	public Player playerTwo = new Player();
	
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
	
	
	// makemove(Player): boolean
		// returns true if this move was the winning move
}
