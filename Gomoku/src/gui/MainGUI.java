package gui;

import java.util.ArrayList;

import com.sun.glass.ui.Screen;

import controller.PVEnvironment;
import controller.PVPlayer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
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
 * This class will setup and organize all the sub GUI's into a main,
 * full-sized, window.
 *         
 * @author manu
 * 
 */
public class MainGUI implements GUICommons
{
    // Screen Dimentions
    private final static double WIDTH = Screen.getMainScreen().getWidth();
    @SuppressWarnings("unused") // just in case
    private final static double HEIGHT = Screen.getMainScreen().getHeight();

    // CSS FILEPATH
    private final static String CUSTOM_CSS_FILENAME = "gomoku.css";

    // Physical GUI properties

    // mainwindow layout
    private static BorderPane mainWindow = new BorderPane();

    // top panel child nodes
    private static Button endGame = new Button("x");
    private static GridPane headerContainer = new GridPane();
    private static Label header = new Label(PLAYER_STATS_HEADER_VALUE);

    // center panel child nodes
    private static ArrayList<ArrayList<Button>> board;
    private static GridPane boardgrid = new GridPane();

    // right panel child nodes
    private static ArrayList<String> playerStats;

    // bottom panel child nodes
    private static Label turnLabel = new Label(TURN_COUNT_LABEL);
    private static Label roundLabel = new Label(ROUND_COUNT_LABEL);
    private static int turnCount;
    private static int roundCount;

    // has user selected PVE mode or PVP mode?
    private static boolean isPVE;

    /**
     * Call this method to place each button in the 2D Array List of buttons
     * into the GridPane boardgrid in their respective order (left-to-right and
     * top-to-bottom).
     * 
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
	// make sure its a new GridPane
	for (int x = 0; x < board.size(); x++)
	{
	    for (int y = 0; y < board.get(x).size(); y++)
	    {
		Button sqrButton = board.get(x).get(y);

		// apply action event handler
		sqrButton.setOnAction(getBoardButtonEventHandler(x, y));

		// add button to gridpane
		GridPane.setConstraints(sqrButton, // Node
			y, x);

		boardgrid.getChildren().add(sqrButton);
	    }
	}

	return boardgrid;
    }

    /**
     * This method will add all the class names to all the global variables.
     * Call this method to add all the CSS class names to all global variables.
     * Only need to call this method once at run time.
     */
    private static void addCSSClassNames()
    {
	endGame.getStyleClass().add(END_GAME_BUTTON_CLASSNAME);
	headerContainer.getStyleClass().add(TOP_PANE_CSS_CLASSNAME);
	headerContainer.setHgap(WIDTH / 2.5);
	boardgrid.getStyleClass().add(GAMEBOARD_GRID_CSS_CLASSNAME);
	header.getStyleClass().add(PLAYER_STATS_HEADER_LABEL_CLASSNAME);
	roundLabel.getStyleClass().add(BOTTOM_PANE_LABEL_CLASSNAME);
	turnLabel.getStyleClass().add(BOTTOM_PANE_LABEL_CLASSNAME);
	for (ArrayList<Button> row : board)
	    for (Button sqrButton : row)
		sqrButton.getStyleClass().add(ACTIVE_BOARD_SQUARE_CLASSNAME);
    }

    /**
     * Call this method to close the window and exit the system should be called
     * when the users have selected an action to quit the game.
     */
    public static void closeApplication()
    {
	Platform.exit();
	System.exit(0);
    }

    /**
     * Call this method to display a alert that will name the winner and the
     * loser, AND prompt the user if they wish to play an other round.
     * 
     * @param wnlException_toString
     */
    public static void displayWinnerAndLoser(String wnlException_toString)
    {

	AlertsAndDialogs aad = new AlertsAndDialogs();
	if (aad.display_newRoundConfirmationAlert(wnlException_toString))
	{
	    if (isPVE)
	    {
		PVEnvironment.playAnOtherRound();
	    } else
	    {
		PVPlayer.playAnotherRound();
	    }
	} else
	{
	    closeApplication();
	}
    }

    /**
     * Call this method to retrieve the child nodes that comprise the top pane
     * of the main borderpane layout.
     * 
     * @return
     */
    private static Node getTopBorderPane()
    {
	Label header = GUICommons.windowHeader(GAME_NAME);

	GridPane.setConstraints(header, 0, 0);
	GridPane.setConstraints(endGame, 1, 0);

	headerContainer.getChildren().addAll(header, endGame);

	return headerContainer;
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

	GridPane grid = new GridPane();

	GridPane.setConstraints(roundLabel, 0, 0);
	GridPane.setConstraints(turnLabel, 1, 0);

	// style
	grid.getChildren().addAll(roundLabel, turnLabel);
	grid.getStyleClass().add(BOTTOM_PANE_CLASSNAME);
	return grid;
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
    private static Node initGameStatsPanel()
    {
	// all the required containers to properly position the player stats
	Group group = new Group();
	VBox mainVbox = new VBox();
	VBox vb = new VBox();
	HBox hb = new HBox();

	// PLAYER STATS BOARD TITLE

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

	return mainVbox;
    }

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
	// new primaryStage
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
	boardgrid = new GridPane();
	setBoard(toBoard);
	mainWindow.setCenter(addBoardToGridPane());
	addCSSClassNames();
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

	// CENTER: Transaction form
	mainWindow.setCenter(addBoardToGridPane());

	// RIGHT:player information/stats
	mainWindow.setRight(initGameStatsPanel());

	// BOTTOM: start game button
	mainWindow.setBottom(initBottomPane());

	// call the method to add all the css class names
	addCSSClassNames();

	MainGUIActionEvents.setQuitApplicationActionEvent();
    }

    /**
     * Call this method to update the button that represents a square on the
     * board game. This method is to be called once a move has been succesfully
     * played. It will update the square at the given x and y coordinates and
     * set the appropriate piece colour on the button.
     * 
     * @param x           is the row position of the square to be updated.
     * @param y           is the column position of the square to be updated.
     * @param pieceColour is the colour of the piece to have displayed on the
     *                    square that is represented by 'w' for white or 'b' for
     *                    black anything else will cause errors.
     */
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
     * Call this method privately to update the text in the lable that contains
     * the round count. The controller should use the update method instead.
     */
    private static void updateRoundCountLabel()
    {
	roundLabel.setText(ROUND_COUNT_LABEL + roundCount);
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
     * Call this method privately to update the text in the label that contains
     * the turn count. The controller should use the update method instead.
     */
    private static void updateTurnCountLabel()
    {
	turnLabel.setText(TURN_COUNT_LABEL + turnCount);
    }
    
    /**
	 * Call this method to setup the event handler for the button at the
	 * specified position of the board variable. When a user clicks an
	 * enabled button (square on the board) the controller will be notified
	 * to play the move. The value returned by the controller should be a
	 * char that represent the 'w' for white, or 'b' for black. this value
	 * is appeneded to the css style class name for the button that will
	 * determine if a white piece or black piece is played.
	 * 
	 * In the event that the GameOver exception is thrown, this will be
	 * caught and signal the alert to notify the user who won and ask if
	 * they wish to play again; by calling the method
	 * getNewRoundConfirmationAlert();
	 * 
	 * @param x of type int represents the row position of board variable
	 * @param y of type int represents the column position of the board
	 *          variable
	 * @return a new event handler for the button at the specified position
	 *         on the board
	 */
	public static EventHandler<ActionEvent> getBoardButtonEventHandler(
		int x, int y)
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

    /**
     * This class will handle all the action events.
     *
     */
    public static class MainGUIActionEvents
    {
	/**
	 * Call this method to call the closeApplication method once the quit
	 * button has been clicked.
	 */
	public static void setQuitApplicationActionEvent()
	{
	    endGame.setOnAction(new EventHandler<ActionEvent>()
	    {
		@Override
		public void handle(ActionEvent event)
		{
		    closeApplication();
		}

	    });
	}

    }
}
