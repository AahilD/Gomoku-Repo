import java.util.ArrayList;

import gui.MainGUI;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author manu
 *
 * Use this class and method to test your
 * code
 */
public class Gomoku extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	public void start(Stage primaryStage)
	{
		ArrayList<ArrayList<Button>> board = new ArrayList<ArrayList<Button>>();
		for (int x = 0; x < 19; x++)
		{
			ArrayList<Button> row = new ArrayList<Button>();
			for(int y = 0; y < 19; y++)
			{
				row.add(new Button((x+1) + ", " + (y+1)));
			}
			board.add(row);
		}
		
		ArrayList<String> playerStats = new ArrayList<String>();
		playerStats.add("P1: manu");
		playerStats.add("wins: 9000");
		playerStats.add("loses: 0");
		playerStats.add("draw: 0");
		playerStats.add("P2: loser");
		playerStats.add("wins: 0");
		playerStats.add("loses: 9000");
		playerStats.add("draw: 0");
		
		MainGUI.mainwindow(board, playerStats, 9000, 50);
	}
}
