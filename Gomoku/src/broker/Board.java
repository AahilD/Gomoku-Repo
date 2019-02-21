package broker;

import java.util.ArrayList;

/**
 * @author GROUP 22
 * Code implemented by Emily Pang
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
		for (int i = 0; i < 19; i++) {
			board = new ArrayList<ArrayList<Square>>();
			for (int x = 0; x<19; i ++)
				board.add(new Square)
			board.add(board);
		}
	}
	
	//Getter
	public ArrayList<ArrayList<Square>> getBoard(){
		return board; 
	}

	//Check Win
	
}