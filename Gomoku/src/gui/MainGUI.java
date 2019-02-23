package gui;

import java.io.IOException;
import java.util.ArrayList;
import controller.GameManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
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
 *         This class will setup and organize all the sub GUI's into a main,
 *         full-sized, window.
 */
public class MainGUI implements GUICommons
{
	private final static String CUSTOM_CSS_FILENAME = "";

	/**
	 * Once the controller has initialized the game it should call this method
	 * with all the necessary parameters to display the game information
	 * 
	 * @param board       a 2D ArrayList of type Button that represents each
	 *                    square on the board
	 * @param playerStats an Arraylist of strings that contain the game stats of
	 *                    each player including their username, info will apear
	 *                    in the order they are set in the ArrayList
	 * @param roundCount  of type int represents the current round count of a
	 *                    session
	 * @param turnCount   of type int represents the current turn count of a
	 *                    game
	 */
	public static void mainwindow(ArrayList<ArrayList<Button>> board,
			ArrayList<String> playerStats, int roundCount, int turnCount)
	{
		Stage primaryStage = new Stage();

		Scene scene = new Scene(
				stageGUI(board, playerStats, roundCount, turnCount));

		Parent root = null; // set root to null for now.

		// try to load custom css
		try
		{
			// get the FXML file
			root = getFXML();
			// if file exists add it to the scene
			scene.setRoot(root);
		} catch (CSSNotFoundException e)
		{
			/**
			 * print warning, but continue with program JAVAFX will just use
			 * default CSS instead since root is not going to be used it will
			 * automatically be sent to java garbage collection
			 */

			System.out.print(e.toString());
		}

		primaryStage.minWidthProperty().bind(scene.widthProperty());
		primaryStage.minHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle("GROUP22");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Call this method to get an object of type Parent of the custom FXML CSS
	 * file. The file name is store in global variable CUSTOM_CSS_FILENAME.
	 * 
	 * @return Parent object of the custom FXML CSS file.
	 * @throws CSSNotFoundException is thrown if the custom CSS file is not
	 *                              found.
	 */
	private static Parent getFXML() throws CSSNotFoundException
	{
		try
		{
			return FXMLLoader
					.load(MainGUI.class.getResource(CUSTOM_CSS_FILENAME));
		} catch (IOException e)
		{
			throw new CSSNotFoundException(CUSTOM_CSS_FILENAME);
		}
	}

	/**
	 * Use this method to coordinate the assembly of the entire UI. This method
	 * currently organizes the differen UI components into a BorderPane layout.
	 * <p>
	 * - The <b>top</b> pane will display the Game title
	 * </p>
	 * <p>
	 * - The <b>left</b> pane is empty for now
	 * </p>
	 * <p>
	 * - The <b>center</b> pane will display the board game itself
	 * </p>
	 * <p>
	 * - The <b>right</b> pane will display information about the players/users
	 * </p>
	 * <p>
	 * - the <b>bottom</b> pane will display the number of games (roundCount);
	 * number of turns (turnCount); and a button to end the game all together.
	 * </p>
	 * <p>
	 * <a href=
	 * "https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html">
	 * Click here for more information on BorderPane... </a>
	 * </p>
	 * 
	 * @param board       is a 2 dimentional ArrayList of type Button that
	 *                    represents the game board to be displayed on the
	 *                    center panel
	 * @param playerStats is an ArrayList of Strings containing player
	 *                    information to be displayed on the right hand panel
	 * @param roundCount  is a counter of type integer that keeps track of how
	 *                    many matches/rounds have been played to be displayed
	 *                    left of the bottom panel
	 * @param turnCount   is a counter of type integer that keeps track of the
	 *                    total turns to be displayed in the center of the
	 *                    bottom panel
	 * @return
	 */
	private static Parent stageGUI(ArrayList<ArrayList<Button>> board,
			ArrayList<String> playerStats, int roundCount, int turnCount)
	{
		// use borderpanes for the main layout
		BorderPane borderpane = new BorderPane();

		// TOP: account information
		borderpane.setTop(GUICommons.windowHeader("Gomoku"));

		// LEFT: empty for now
		// perhaps we could display the moves played through out the game
		// [username] played on: [x]:[y]
		// [username] played on: [x]:[y]
		// etc..

		// CENTER: Transaction form
		borderpane.setCenter(displayBoard(board));

		// RIGHT:player information/stats
		borderpane.setRight(setupGameStatsPanel(playerStats));

		// BOTTOM: start game button
		borderpane.setBottom(setupBottomPane(roundCount, turnCount));

		return borderpane;
	}

	/**
	 * Call this method to display the buttons on the board. Each button
	 * represents a square of the Gomoku board. Each button on the insitiall
	 * start up, each button is set to default values. Only on a mouse event
	 * will the button change state. On a click event the controller will
	 * determine the new state of the button. Each button will be placed in a
	 * GridPane.
	 * <p>
	 * <a href=
	 * "https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/GridPane.html">
	 * Click here for more information on GridPane... </a>
	 * </p>
	 * 
	 * @param board is a 2 dimentional matrix ArrayList of type Button that
	 *              represents the board composition
	 * @return
	 */
	private static Node displayBoard(ArrayList<ArrayList<Button>> board)
	{
		int i = 0;
		GridPane boardgrid = new GridPane();
		for (int x = 0; x < board.size(); x++)
		{
			for (int y = 0; y < board.get(x).size(); y++)
			{
				Button sqrButton = board.get(x).get(y);
				sqrButton.setPrefWidth(35);
				sqrButton.setPrefHeight(35);
				sqrButton.setId(x + "," + y); // use the id to store x and y
												// value

				sqrButton.setOnAction(getBoardButtonEventHandler(sqrButton));

				GridPane.setConstraints(
						sqrButton, // Node
						y, x); // Insets (padding)
				boardgrid.getChildren().add(sqrButton);
				boardgrid.getChildren().get(i).setId("bt" + i);
			}
			i++;
		}
		boardgrid.minHeight(1000);
		boardgrid.minHeight(1000);
		return boardgrid;
	}

	/**
	 * Use this method to setup an event handler for each button on the board
	 * game itself. This is not for other buttons. This will call the
	 * appropriate method in the controller class that will treat the action as
	 * a player having made a move by having provoked this event handler. The
	 * button will also reflect any changes as per the controller.
	 * 
	 * @param sqrButton a Button that represents a square on the grid of the
	 *                  board game.
	 * @return the EventHandler for this button
	 */
	private static EventHandler<ActionEvent> getBoardButtonEventHandler(
			Button sqrButton)
	{
		return new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// parse through the id to grab the x and y values "x,y"
				String[] xy = sqrButton.getId().split(",");
				int x = Integer.parseInt(xy[0]);
				int y = Integer.parseInt(xy[1]);
				sqrButton.setText(GameManager.playMove(x, y));
				sqrButton.setDisable(true);
			}
		};
	}

	/**
	 * Use this method to set up the player stats/information panel. It will
	 * display each string from the ArrayList on an individual line in the same
	 * order as they are stored in the ArrayList. each string is currently
	 * allocated to their own respective Label and each Label will be added to a
	 * sing VBox container.
	 * 
	 * <p>
	 * <a href=
	 * "https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/VBox.html">
	 * Click here for more information on VBox </a>
	 * </p>
	 * 
	 * @param playerStats is an array list of strings that represent each line
	 *                    to display
	 * @return Node of type VBox composed of individual labels from each string
	 *         in the ArrayList
	 */
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

	/**
	 * Call this method to setup the content of the bottom pane. Currently, the
	 * bottom pane will be composed of the number of rounds, the number of
	 * turns, and an additional button to quit the game. Each item is placed in
	 * an HBox that will display each item from left to right in the respective
	 * order.
	 * <p>
	 * <a href=
	 * "https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/HBox.html">
	 * Click here for more information on HBox </a>
	 * </p>
	 * 
	 * @param roundCount is an integer that represents the number of rounds
	 *                   played
	 * @param turnCount  is an integer that represents the total number of turns
	 *                   played
	 * @return Node of type HBox containing the respective labels and the quit
	 *         button
	 */
	private static Node setupBottomPane(int roundCount, int turnCount)
	{
		Label roundLabel = new Label("Round: " + roundCount);
		Label turnLabel = new Label("turns: " + turnCount);
		Button endGame = new Button("End Game");

		endGame.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Platform.exit();
				System.exit(0);
			}

		});

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
