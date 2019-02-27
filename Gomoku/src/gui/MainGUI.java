package gui;

import java.util.ArrayList;

import com.sun.glass.ui.Screen;

import controller.GameManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author manu
 * 
 *         This class will setup and organize all the sub GUI's into a main,
 *         full-sized, window.
 */
public class MainGUI implements GUICommons
{
	// WINDOW DIMENTIONS
	private final static double WIDTH = Screen.getMainScreen().getWidth();
	@SuppressWarnings("unused")
	private final static double HEIGHT = Screen.getMainScreen().getHeight();

	// CSS FILEPATH
	private final static String CUSTOM_CSS_FILENAME = "css/gomoku.css";

	// CSS CLASS NAMES
	private static final String BOTTOM_PANE_CLASSNAME = "bottom-pane";
	private static final String BOTTOM_PANE_LABEL_CLASSNAME = "bottom-pane-labels";
	private static final String END_GAME_BUTTON_CLASSNAME = "end-game-button";
	private static final String PLAYER_STATS_VBOX_CLASSNAME = "player-stats-vbox";
	private static final String PLAYER_STATS_LABELS_CLASSNAME = "player-stats-label";
	private static final String ACTIVE_BOARD_SQUARE_CLASSNAME = "active-board-square";
	private static final String OCUPIED_BOARD_SQUARE_CLASSNAME = "ocupied-board-square-";
	private static final String PLAYER_STATS_HEADER_LABEL_CLASSNAME = "player-stats-header";

	// LABEL VALUES
	private static final String GAME_NAME = "Gomoku";
	private static final String PLAYER_STATS_HEADER_VALUE = "Player Panel Board";
	private static final String ROUND_COUNT_LABEL = "Round: ";
	private static final String TURN_COUNT_LABEL = "Turns: ";
	private static final String GAMEBOARD_GRID_CSS_CLASSNAME = "gridpane-game-board";

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

		//primaryStage.setWidth(WIDTH);
		//primaryStage.setHeight(HEIGHT);
		// get the FXML file
		// if file exists add it to the scene
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle(TITLE_BAR_NAME);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(scene);
		// TODO make custom task/title-bar
		// primaryStage.initStyle(StageStyle.UNDECORATED);

		primaryStage.show();

		// load css
		scene.getStylesheets().add(MainGUI.class
				.getResource(CUSTOM_CSS_FILENAME).toExternalForm());

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
		borderpane.setTop(GUICommons.windowHeader(GAME_NAME));

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
				sqrButton.getStyleClass().add(ACTIVE_BOARD_SQUARE_CLASSNAME);

				// id to store x and y value for later
				sqrButton.setId(x + "," + y);

				// apply action event handler
				sqrButton.setOnAction(getBoardButtonEventHandler(sqrButton));

				// add button to gridpane
				GridPane.setConstraints(
						sqrButton, // Node
						y, x); // Insets (padding)
				boardgrid.getChildren().add(sqrButton);
				boardgrid.getChildren().get(i).setId(x + "," + y);
			}
			i++;
		}
		
		// STYLIZE
		boardgrid.getStyleClass().add(GAMEBOARD_GRID_CSS_CLASSNAME);
		
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
				sqrButton.setDisable(true);
				String output = OCUPIED_BOARD_SQUARE_CLASSNAME
						+ GameManager.playMove(x, y);
				sqrButton.getStyleClass().add(output);
				sqrButton.applyCss();
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
		Group group = new Group();
		VBox mainVbox = new VBox();
		VBox vb = new VBox();
		HBox hb = new HBox();

		// PLAYER STATS BOARD TITLE
		Label header = new Label(PLAYER_STATS_HEADER_VALUE);
		// add css style
		header.getStyleClass().add(PLAYER_STATS_HEADER_LABEL_CLASSNAME);
		//hb.minWidth(Double.MAX_VALUE);
		//hb.setAlignment(Pos.CENTER);
		hb.getChildren().add(header);
		
		// add the hb containing the header label into the vb
		vb.getChildren().add(hb);
		
		
		GridPane gp = new GridPane();
		
		// cycle through each string in the ArrayList<String> playerStats
		for (int i = 0; i < playerStats.size(); i++)
		{
			String[] splitStr = playerStats.get(i).split(":");
			
			Label placeholder = new Label(splitStr[0]);
			placeholder.getStyleClass().add(PLAYER_STATS_LABELS_CLASSNAME);
			
			Label valueholder = new Label(splitStr[1]);
			
			GridPane.setConstraints(placeholder, 0, i);
			GridPane.setConstraints(valueholder, 1, i);
			gp.getChildren().addAll(placeholder, valueholder);
		}
		vb.getStyleClass().add(PLAYER_STATS_VBOX_CLASSNAME);
		vb.getChildren().add(gp);
		

		group.getChildren().add(vb);
		mainVbox.getChildren().add(group);
		// center it
		mainVbox.setAlignment(Pos.CENTER);
		
		
		return mainVbox;
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
		// labels
		Label roundLabel = new Label(ROUND_COUNT_LABEL + roundCount);
		Label turnLabel = new Label(TURN_COUNT_LABEL + turnCount);

		// button
		Button endGame = new Button("End Game");

		// styles
		roundLabel.getStyleClass().add(BOTTOM_PANE_LABEL_CLASSNAME);
		turnLabel.getStyleClass().add(BOTTOM_PANE_LABEL_CLASSNAME);

		endGame.getStyleClass().add(END_GAME_BUTTON_CLASSNAME);

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

		// style
		//grid.setPadding(GUICommons.DEFAULT_PADDING);
		grid.setMaxWidth(Double.MAX_VALUE);
		//grid.setAlignment(Pos.CENTER);
		grid.setHgap(WIDTH / 2.75);
		grid.getChildren().addAll(roundLabel, turnLabel, endGame);
		grid.getStyleClass().add(BOTTOM_PANE_CLASSNAME);
		return grid;
	}

}
