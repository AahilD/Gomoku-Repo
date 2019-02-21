package broker;

/**
 * @author GROUP 22
 *
 * This class represents an object of type Player
 * which will contain and maintain all the relevant
 * player profile.
 * 
 */

public class Player
{
	// TODO implement variables, constructor(s), getters, setters, and other methods
	private String userName;
	private int winCount;
	private int loseCount;
	private int drawCount;
	
	//Getter
	public String getUserName() {
		return userName;
	}
	public int getWinCount() {
		return winCount;
	}
	public int getLoseCount() {
		return loseCount;
	}
	public int getDrawCount() {
		return drawCount;
	}
	
	
	//Setter
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}
	public void setLoseCount(int loseCount) {
		this.loseCount = loseCount;
	}
	public void setDrawCount(int drawCount) {
		this.drawCount = drawCount;
	}
	
}
