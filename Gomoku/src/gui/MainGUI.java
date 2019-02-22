package gui;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author manu
 * 
 * This class will setup and organize all the sub GUI's
 * into a main, full-sized, window.
 */
public class MainGUI implements GUICommons
{
	
	/**
	 * Once the controller has initialized the game
	 * it should call this method with all the 
	 * necessary parameters to display the game information
	 * 
	 * @param board a 2D ArrayList of type Button that represents each square on the board
	 * @param playerStats an Arraylist of strings that contain the game stats of each player including their username, info will apear in the order they are set in the ArrayList
	 * @param roundCount of type int represents the current round count of a session
	 * @param turnCount of type int represents the current turn count of a game
	 */
	public static void mainwindow(ArrayList<ArrayList<Button>> board, ArrayList<String> playerStats, int roundCount, int turnCount)
	{
		Stage primaryStage = new Stage();
		Scene scene = new Scene(stageGUI(board, playerStats, roundCount, turnCount));
		primaryStage.minWidthProperty().bind(scene.widthProperty());
		primaryStage.minHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle("GROUP22");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static Parent stageGUI(ArrayList<ArrayList<Button>> board, ArrayList<String> playerStats, int roundCount, int turnCount)
	{
		// use borderpanes for the main layout
		BorderPane borderpane = new BorderPane();
		
		// TOP: account information
		borderpane.setTop(GUICommons.windowHeader("Gomoku"));
		
		// LEFT: Nothing
					// - would like to ahve a feature here that displays move histogram i.e: player one: [0][1]
		
		// CENTER: Transaction form
		borderpane.setCenter(displayBoard(board));
		
		// RIGHT:
		borderpane.setRight(setupGameStatsPanel(playerStats));
		
		// BOTTOM: start game button
		borderpane.setBottom(setupBottomPane(roundCount, turnCount));
		
		
		
		return borderpane;
	}
	
	private static Node displayBoard(ArrayList<ArrayList<Button>> board)
	{
		int i = 0;
		GridPane boardgrid = new GridPane();
		for (int x = 0; x < board.size(); x++)
		{
			for (int y = 0; y < board.get(x).size(); y++)
			{
				Button sqrButton = board.get(x).get(y);
				sqrButton.setPrefWidth(60);
				sqrButton.setPrefHeight(60);
				GridPane.setConstraints(
						sqrButton, //Node 
						y, x); // Insets (padding)
				boardgrid.getChildren().add(sqrButton);
				boardgrid.getChildren().get(i).setId("bt"+i);
			}
			i++;
		}
		boardgrid.minHeight(1000);
		boardgrid.minHeight(1000);
		return boardgrid;
	}
	
	private static Node setupGameStatsPanel(ArrayList<String> playerStats)
	{
		VBox box = new VBox();
		for (int i = 0; i < playerStats.size(); i++)
		{
			Label label = new Label(playerStats.get(i));
			label.setPadding(GUICommons.defaultPadding());
			label.maxWidth(Double.MAX_VALUE);
			label.setAlignment(Pos.CENTER_LEFT);
			box.getChildren().add(label);
		}
		
		return box;
	}
	
	private static Node setupBottomPane(int roundCount, int turnCount)
	{
		Label roundLabel = new Label("Round: " + roundCount);
		Label turnLabel = new Label("turns: " + turnCount);
		Button endGame = new Button("End Game");
		
		GridPane grid = new GridPane();
		
		GridPane.setConstraints(roundLabel, 0, 0);
		GridPane.setConstraints(turnLabel, 1, 0);
		GridPane.setConstraints(endGame, 2, 0);
		grid.setPadding(GUICommons.defaultPadding());
		grid.setMaxWidth(Double.MAX_VALUE);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(300);
		grid.getChildren().addAll(roundLabel, turnLabel, endGame);
		
		return grid;
	}

}
