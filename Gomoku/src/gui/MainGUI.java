package gui;

import java.util.ArrayList;
import java.util.Optional;

import com.sun.glass.ui.Screen;

import controller.GameController;
import controller.GameOverException;
import controller.PVEnvironment;
import controller.PVPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
    private final static String CUSTOM_CSS_FILENAME = "gomoku.css";
    private static final String TOP_PANE_CSS_CLASSNAME = "top-pane-hbox";

    // Physical GUI properties
    private static BorderPane mainWindow = new BorderPane();
    private static ArrayList<ArrayList<Button>> board;
    private static ArrayList<String> playerStats;
    private static int turnCount;
    private static int roundCount;
    private static Label turnLabel = new Label(TURN_COUNT_LABEL);
    private static Label roundLabel = new Label(ROUND_COUNT_LABEL);
    private static GameController gControl;
    // Until I find a better way
    private static boolean isPVE;

    /**
     * Once the controller has initialized the game it should call this method
     * with all the necessary parameters to display the game information
     * 
     * @param toBoard       a 2D ArrayList of type Button that represents each
     *                      square on the board
     * @param toPlayerStats an Arraylist of strings that contain the game stats
     *                      of each player including their username, info will
     *                      apear in the order they are set in the ArrayList
     * @param roundCount    of type int represents the current round count of a
     *                      session
     * @param turnCount     of type int represents the current turn count of a
     *                      game
     */
    public static void initMainWindow(ArrayList<ArrayList<Button>> toBoard,
	    ArrayList<String> toPlayerStats, int roundCount, int turnCount,
	    boolean toIsPVE)
    {
	Stage primaryStage = new Stage();

	// set the values
	isPVE = toIsPVE;
	setBoard(toBoard);
	setPlayerStatsList(toPlayerStats);
	setTurnCount(turnCount);
	setRoundCount(roundCount);

	// stage the main window
	stageMainWindow();

	// add the main window to scene
	Scene scene = new Scene(mainWindow);

	// undecorated + full screen no title bar
	primaryStage.initStyle(StageStyle.UNDECORATED);
	primaryStage.setFullScreen(true);
	primaryStage.setScene(scene);
	primaryStage.show();

	// ApplyCSS
	GUICommons.applyCSS(scene, CUSTOM_CSS_FILENAME);
    }

    /**
     * The controller may call this method to reset the board with a new 2D
     * ArrayList of Buttons. The changes will automatically be updated in the
     * GUI.
     * 
     * @param toBoard is a 2D ArrayList of type <Button>, each button
     *                representing a square on the board.
     */
    public static void resetBoard(ArrayList<ArrayList<Button>> toBoard)
    {
	setBoard(toBoard);
	mainWindow.setCenter(addBoardToGridPane());
    }

    public static void closeApplication()
    {
	Platform.exit();
	System.exit(0);
    }

    public static void updateBoardSquareButton(int x, int y, char pieceColour)
    {
	Button sqr2update = board.get(x).get(y);
	Button sqrButton = board.get(x).get(y);
	sqrButton.setDisable(true);
	sqr2update.getStyleClass()
		.add(OCUPIED_BOARD_SQUARE_CLASSNAME + pieceColour);
	sqr2update.applyCss();
    }

    /**
     * The controller may call this method to updatePlayerStatsPanel, by passing
     * in a new set of playerstats in an ArrayList of type <String>. Each item
     * represents a new line on the player stats panel. Each string should
     * contain a colon to seperate the label and the value.
     * 
     * @param toPlayerStats an ArrayList of type <String>, Each item represents
     *                      a new line, and each string should contain a colon
     *                      to seperate the label from the value.
     */
    public static void updatePlayerStatsPanel(ArrayList<String> toPlayerStats)
    {
	setPlayerStatsList(toPlayerStats);
	mainWindow.setRight(initGameStatsPanel());
    }

    /**
     * The controller may call this method to update the roundCount value
     * displayed on the GUI. Changes to the value will automatically display on
     * the GUI in this method. To updateRoundCount and reset turncount, call the
     * method updateRoundCount_ResetTurnCount().
     * 
     * @param toRoundCount is an variable of type int that represents the number
     *                     of rounds played by the same two players.
     */
    public static void updateRoundCount(int toRoundCount)
    {
	setRoundCount(toRoundCount);
	updateTurnCountLabel();
    }

    /**
     * The controller may call this method to update the turnCount value
     * displayed on the GUI. Changes to the value will automatically display on
     * the GUI by calling this method.
     * 
     * @param toTurnCount is a variable of type int that represents the number
     *                    of turns played in a single round.
     */
    public static void updateTurnCount(int toTurnCount)
    {
	setTurnCount(toTurnCount);
	updateRoundCountLabel();
    }

    /**
     * The controller may call this method to update round count and reset turn
     * count in one method call. After updating the round count to the value
     * passed in to the argument, turn count will default to 0.
     * 
     * @param toRoundCount
     */
    public static void updateRoundCount_ResetTurnCount(int toRoundCount)
    {
	updateRoundCount(toRoundCount);
	updateTurnCount(0);
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
     * @param toBoard     is a 2 dimentional ArrayList of type Button that
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
    private static void stageMainWindow()
    {
	// use borderpanes for the main layout

	// TOP: account information
	mainWindow.setTop(getTopBorderPane());

	// LEFT: empty for now
	// perhaps we could display the moves played through out the game
	// [username] played on: [x]:[y]
	// [username] played on: [x]:[y]
	// etc..

	// CENTER: Transaction form
	mainWindow.setCenter(addBoardToGridPane());

	// RIGHT:player information/stats
	mainWindow.setRight(initGameStatsPanel());

	// BOTTOM: start game button
	mainWindow.setBottom(initBottomPane());
    }

    private static Node getTopBorderPane()
    {
	// button
	GridPane container = new GridPane();
	
	Button endGame = new Button("x");
	
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
	
	Label header = GUICommons.windowHeader(GAME_NAME);
	
	GridPane.setConstraints(header, 0, 0);
	GridPane.setConstraints(endGame, 1, 0);
	
	container.getChildren().addAll(header, endGame);
	
	container.setHgap(WIDTH / 2.5);
	
	container.getStyleClass().add(TOP_PANE_CSS_CLASSNAME);
	
	return container;
    }

    /**
     * Call this method privately to set the global variable roundCount. The
     * controller should use the public method updateRoundCount, or
     * updateRoundCount_ResetTurnCount.
     * 
     * @param toRoundCount is a variable of type int that represents the number
     *                     of rounds played.
     */
    private static void setRoundCount(int toRoundCount)
    {
	roundCount = toRoundCount;
    }

    /**
     * Call this method privately to set the turn count. The controller should
     * use the method updateTurnCount instead.
     * 
     * @param toTurnCount
     */
    private static void setTurnCount(int toTurnCount)
    {
	turnCount = toTurnCount;
	updateTurnCountLabel();
    }

    /**
     * Call this method privately to set the player stats. The controller should
     * call updatePlayerStatsPanel instead.
     * 
     * @param toPlayerStats an ArrayList of type String containing player stats
     */
    private static void setPlayerStatsList(ArrayList<String> toPlayerStats)
    {
	playerStats = toPlayerStats;
    }

    /**
     * Call this method privately to set the board with new values, the
     * Controller should use the resetBoard method, or the
     * updateBoardSquareButton method isntead.
     * 
     * @param toBoard a 2d ArrayList of type <Button> representing each square
     *                on the board.
     */
    private static void setBoard(ArrayList<ArrayList<Button>> toBoard)
    {
	board = new ArrayList<ArrayList<Button>>();

	for (int x = 0; x < toBoard.size(); x++)
	{

	    ArrayList<Button> row = new ArrayList<Button>();

	    for (int y = 0; y < toBoard.get(x).size(); y++)
		row.add(toBoard.get(x).get(y));

	    board.add(row);
	}

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
    private static Node addBoardToGridPane()
    {
	GridPane boardgrid = new GridPane();

	for (int x = 0; x < board.size(); x++)
	{
	    for (int y = 0; y < board.get(x).size(); y++)
	    {
		Button sqrButton = board.get(x).get(y);

		// STYLE - PLAYABLE SQUARE
		sqrButton.getStyleClass().add(ACTIVE_BOARD_SQUARE_CLASSNAME);

		// apply action event handler
		sqrButton.setOnAction(getBoardButtonEventHandler(x, y));

		// add button to gridpane
		GridPane.setConstraints(sqrButton, // Node
			y, x);

		boardgrid.getChildren().add(sqrButton);
	    }
	}

	// STYLIZE - GridPane
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
    private static Node initGameStatsPanel()
    {
	Group group = new Group();
	VBox mainVbox = new VBox();
	VBox vb = new VBox();
	HBox hb = new HBox();

	// PLAYER STATS BOARD TITLE
	Label header = new Label(PLAYER_STATS_HEADER_VALUE);
	// add css style
	header.getStyleClass().add(PLAYER_STATS_HEADER_LABEL_CLASSNAME);
	// hb.minWidth(Double.MAX_VALUE);
	// hb.setAlignment(Pos.CENTER);
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
    private static Node initBottomPane()
    {
	// labels
	roundLabel = new Label(ROUND_COUNT_LABEL + roundCount);
	turnLabel = new Label(TURN_COUNT_LABEL + turnCount);
	
	// styles
	roundLabel.getStyleClass().add(BOTTOM_PANE_LABEL_CLASSNAME);
	turnLabel.getStyleClass().add(BOTTOM_PANE_LABEL_CLASSNAME);


	GridPane grid = new GridPane();

	GridPane.setConstraints(roundLabel, 0, 0);
	GridPane.setConstraints(turnLabel, 1, 0);
	//GridPane.setConstraints(endGame, 2, 0);

	// style
	// grid.setPadding(GUICommons.DEFAULT_PADDING);
	grid.setMaxWidth(Double.MAX_VALUE);
	// grid.setAlignment(Pos.CENTER);
	grid.setHgap(WIDTH / 2.75);
	grid.getChildren().addAll(roundLabel, turnLabel/*, endGame*/);
	grid.getStyleClass().add(BOTTOM_PANE_CLASSNAME);
	return grid;
    }

    /**
     * Call this method privately to update the text in the label that contains
     * the turn count. The controller should use the update method instead.
     */
    private static void updateTurnCountLabel()
    {
	turnLabel.setText(TURN_COUNT_LABEL + turnCount);
    }

    /**
     * Call this method privately to update the text in the lable that contains
     * the round count. The controller should use the update method instead.
     */
    private static void updateRoundCountLabel()
    {
	roundLabel.setText(ROUND_COUNT_LABEL + roundCount);
    }

    /**
     * Call this method to setup the event handler for the button at the
     * specified position of the board variable. When a user clicks an enabled
     * button (square on the board) the controller will be notified to play the
     * move. The value returned by the controller should be a char that
     * represent the 'w' for white, or 'b' for black. this value is appeneded to
     * the css style class name for the button that will determine if a white
     * piece or black piece is played.
     * 
     * In the event that the GameOver exception is thrown, this will be caught
     * and signal the alert to notify the user who won and ask if they wish to
     * play again; by calling the method getNewRoundConfirmationAlert();
     * 
     * @param x of type int represents the row position of board variable
     * @param y of type int represents the column position of the board variable
     * @return a new event handler for the button at the specified position on
     *         the board
     */
    public static EventHandler<ActionEvent> getBoardButtonEventHandler(int x,
	    int y)
    {
	return new EventHandler<ActionEvent>()
	{
	    @Override
	    public void handle(ActionEvent event)
	    {
		if (isPVE)
		{
		    PVEnvironment.playMoveAt(x, y);
		} else
		    PVPlayer.playMoveAt(x, y);

	    }
	};
    }
}
