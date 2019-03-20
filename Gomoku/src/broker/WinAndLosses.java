package broker;

public class WinAndLosses extends Exception {

	//Instance variables
	private Player winner;
	private Player loser;
	
	//Constructor
	public WinAndLosses (Player aWinner, Player aLoser)
	{
		winner = new Player(aWinner);
		loser = new Player(aLoser);
	}
	
	//Getters and setters
	public Player getWinner()
	{
		return new Player(winner);
	}
	
	public void setWinner(Player aPlayer)
	{
		winner = new Player(aPlayer);
	}
	
	public Player getLoser()
	{
		return new Player(loser);
	}
	
	public void setLoser(Player aPlayer)
	{
		loser = new Player(aPlayer);
	}
}
