package broker;

import java.util.ArrayList;

/**
 * @author GROUP 22
 *
 * This class represents and Ojbect of Type Board.
 * An instance of this class will contain and maintain
 * the state of the current game being played.
 *  
 */

public class Board
{
	// TODO implement variables, constructor(s), getters, setters, and other methods
	// TODO to set up the board we need a 19x19 ArrayList of type <Square>
	
	private ArrayList<ArrayList<Square>> board;
	
	//Constructor
	public Board() {
		board = new ArrayList<ArrayList<Square>>();
		for (int i = 0; i < 19; i++) {
			board.add(new ArrayList<Square>());
		}
	}
	
	//Getter
	public ArrayList<ArrayList<Square>> getBoard(){
		return board; 
	}

}