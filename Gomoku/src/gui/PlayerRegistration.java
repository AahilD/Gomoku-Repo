package gui;

import java.util.ArrayList;

import com.sun.glass.ui.Screen;

import controller.PVEnvironment;
import controller.PVPlayer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// TODO @Aahil finish fixing the styling for Player Registration
// if level 2 has you allready too busy, please let me know I will take this on over the weekend.

/**
 * @author GROUP 22
 * @author Emmanuel
 * 
 *         This class is the main application launcher. This GUI will prompt
 *         user to select either player versus player mode (PVP), or, player
 *         versus environment mode (PVE). The user(s) will be prompted to enter
 *         a user name. Start Game button will open the MainGUI to start
 *         playing.
 * 
 */
public class PlayerRegistration extends Application implements GUICommons
{
    // WINDOW DIMENTIONS
    private static double WIDTH = Screen.getMainScreen().getWidth()
	    - (Screen.getMainScreen().getWidth()) / 2;
    private static double HEIGHT = Screen.getMainScreen().getHeight()
	    - (Screen.getMainScreen().getHeight()) / 5;

    // Strings
    private static final String USERNAME_TEXTINPUT_PLACEHOLDER_VALUE = "Enter username: ";
    private static final String START_GAME_BUTTON_TEXT_VALUE = "Start Game";
    private static final String PLAYER_REGISTRATION_HEADER_TEXT_VALUE = "Player Registration";
    private static final String PVPLAYER_LABEL_TEXT_VALUE = "PvP";
    private static final String PVENVIRONMENT_LABEL_TEXT_VALUE = "PvE";

    // Level values in int[]
    // as you add levels include them in this array
    private static final int[] LEVEL = { 0, 1, 2 };

    // labels

    private static final Label ENTER_UNAME_LABEL1 = new Label(
	    USERNAME_TEXTINPUT_PLACEHOLDER_VALUE);
    private static final Label ENTER_UNAME_LABEL2 = new Label(
	    USERNAME_TEXTINPUT_PLACEHOLDER_VALUE);
    private static final Label LEVEL_SELECTION_LABEL = new Label("Label");

    // The TextField for player 1 user name
    private static TextField PLAYER_ONE_USERNAME = new TextField("Player 1");
    // The TextField for player 2 user name
    private static TextField PLAYER_TWO_USERNAME = new TextField("Player 2");

    // toggle group and Radio Buttons
    // PLAYER VS PLAYER (2 PLAYER)
    private static ToggleGroup opponentMode;
    private static RadioButton playerVplayerRB = new RadioButton(
	    PVPLAYER_LABEL_TEXT_VALUE);

    // PLAYER VS ENVRIONMENT (SINGLE_PLAYER)
    private static RadioButton playerVEnvironmentRB = new RadioButton(
	    PVENVIRONMENT_LABEL_TEXT_VALUE);

    private static ToggleGroup levelSelector;
    private static ArrayList<RadioButton> levelOptions_rbList;

    /**
     * This is the official main method that will be called to launch the
     * application.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
	launch(args);
    }

    /**
     * (non-Javadoc)
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage) Called by
     *      the main method of this class.
     */
    @Override
    public void start(Stage primaryStage)
    {
	//
	Scene scene = new Scene(stageGUI());
	// primaryStage.minWidthProperty().bind(scene.widthProperty());
//		primaryStage.minHeightProperty().bind(scene.heightProperty());
	primaryStage.setMinHeight(HEIGHT);
	primaryStage.setMinWidth(WIDTH);
	primaryStage.setTitle(GUICommons.TITLE_BAR_NAME);
	primaryStage.setScene(scene);
	primaryStage.show();

	scene.getStylesheets().add(MainGUI.class
		.getResource(CUSTOM_CSS_FILENAME).toExternalForm());
    }

    /**
     * Use this method to manage the setup for the main window of the user
     * registration GUI. Currently uses BorderPane for the final window layout.
     * 
     * @return Node of type BorderPane composed of various sub-nodes
     */
    private BorderPane stageGUI()
    {
	// use borderpanes for the main layout
	BorderPane borderpane = new BorderPane();

	// TOP: account information
	borderpane.setTop(
		GUICommons.windowHeader(PLAYER_REGISTRATION_HEADER_TEXT_VALUE));
	// CENTER: Transaction form
	borderpane.setCenter(setupPlayerInfoForm());
	borderpane.getCenter().getStyleClass().add(PR_FORMGRID_CLASSNAME);
	// BOTTOM: start game button
	borderpane.setBottom(startGameButton());

	// LEFT: Nothing
	// RIGHT: Nothing

	return borderpane;
    }

    /**
     * This method creates a button that will notify the controller to start the
     * game, sending the textfields values as the usernames for each player.
     * 
     * @return Node of HBox containing the button to start the game.
     */
    private Node startGameButton()
    {
	Button startGame = new Button(START_GAME_BUTTON_TEXT_VALUE);
	startGame.getStyleClass().add(BUTTON_CSS_CLASSNAME);
	startGame.setOnAction(new EventHandler<ActionEvent>()
	{

	    @Override
	    public void handle(ActionEvent event)
	    {
		Stage stage = (Stage) startGame.getScene().getWindow();
		stage.close();

		if (playerVplayerRB.isSelected())
		{
		    PVPlayer.initializeGame(PLAYER_ONE_USERNAME.getText(),
			    PLAYER_TWO_USERNAME.getText());

		} else if (playerVEnvironmentRB.isSelected())
		{
		    RadioButton lvl = (RadioButton) levelSelector.getSelectedToggle();
		    PVEnvironment.initializeGame(PLAYER_ONE_USERNAME.getText(), Integer.parseInt(lvl.getText()));

		} else
		{
		    System.out.println(
			    "Something went wrong with the radio buttons");
		}

	    }
	});
	HBox box = new HBox(startGame);
	box.setMaxWidth(Double.MAX_VALUE);
	box.setAlignment(Pos.CENTER_RIGHT);
	box.setPadding(GUICommons.DEFAULT_PADDING);
	return box;
    }

    /**
     * This method is in charge of setting up the player registration form.
     * where players will be able to enter their username. Each item is placed
     * inside a GridPane to manage the alignment.
     * 
     * @return Node of type GridPane containing user registration form
     */
    private Node setupPlayerInfoForm()
    {
	// Radio buttons
	// RB group
	opponentMode = new ToggleGroup();

	// RB 1: PvP
	playerVplayerRB.setToggleGroup(opponentMode);
	playerVplayerRB.setSelected(true);

	// RB 2: PvE
	playerVEnvironmentRB.setToggleGroup(opponentMode);

	// add radioButtons to hbox
	HBox opponentModeModeHbox = new HBox();
	opponentModeModeHbox.getChildren().addAll(playerVplayerRB,
		playerVEnvironmentRB);

	// level selector
	init_levelSelector();

	HBox levelSelectionHbox = new HBox();
	levelSelectionHbox.getChildren().addAll(levelOptions_rbList);

	// Style the nodes
	// radio buttons
	opponentModeModeHbox.setMaxWidth(Double.MAX_VALUE);
	opponentModeModeHbox.setAlignment(Pos.CENTER);
	playerVplayerRB.getStyleClass().add(RADIO_BUTTON_CSS_CLASSNAME);
	playerVEnvironmentRB.getStyleClass().add(RADIO_BUTTON_CSS_CLASSNAME);
	ENTER_UNAME_LABEL1.getStyleClass().add(FORM_LABEL_CSS_CLASSNAME);
	ENTER_UNAME_LABEL2.getStyleClass().add(FORM_LABEL_CSS_CLASSNAME);

	// create GridPane
	GridPane form = new GridPane();

	// add all nodes to grid pane

	GridPane.setColumnSpan(opponentModeModeHbox, 2); // span both col
	GridPane.setRowIndex(opponentModeModeHbox, 0);// row 0
	GridPane.setColumnSpan(levelSelectionHbox, 2); // span both col
	GridPane.setRowIndex(levelSelectionHbox, 1); // row 1
	GridPane.setConstraints(ENTER_UNAME_LABEL1, 0, 2); // col=0 row 2
	GridPane.setConstraints(PLAYER_ONE_USERNAME, 1, 2); // col=1 row 2
	GridPane.setConstraints(ENTER_UNAME_LABEL2, 0, 3); // col=0 row 3
	GridPane.setConstraints(PLAYER_TWO_USERNAME, 1, 3); // col=1 row 3

	// add children nodes
	form.getChildren().addAll(opponentModeModeHbox, levelSelectionHbox,
		ENTER_UNAME_LABEL1, PLAYER_ONE_USERNAME, ENTER_UNAME_LABEL2,
		PLAYER_TWO_USERNAME);

	// Style grid pane
	form.setHgap(10);
	form.setVgap(10);
	form.setPadding(GUICommons.DEFAULT_PADDING);
	form.setMaxWidth(Double.MAX_VALUE);
	form.setAlignment(Pos.CENTER);
	form.setPadding(GUICommons.DEFAULT_PADDING);

	opponentMode.selectedToggleProperty().addListener(getToggleListener());

	return form;
    }

    private ChangeListener<? super Toggle> getToggleListener()
    {
	return new ChangeListener<Toggle>()
	{

	    @Override
	    public void changed(ObservableValue<? extends Toggle> observable,
		    Toggle oldValue, Toggle newValue)
	    {
		RadioButton modeRb = (RadioButton) opponentMode
			.getSelectedToggle();

		// TODO EMMANUEL IMPROVE THIS CODE
		// if the user selected single player mode than hide
		// the option to enter the player 2 user name
		if (modeRb.getText().equals(PVENVIRONMENT_LABEL_TEXT_VALUE))
		{
		    ENTER_UNAME_LABEL2.setVisible(false);
		    PLAYER_TWO_USERNAME.setVisible(false);

		    for (RadioButton lvlRb : levelOptions_rbList)
		    {
			lvlRb.setVisible(true);
			lvlRb.setDisable(false);
		    }
		} else if (modeRb.getText()
			.contentEquals(PVPLAYER_LABEL_TEXT_VALUE))
		{
		    ENTER_UNAME_LABEL2.setVisible(true);
		    PLAYER_TWO_USERNAME.setVisible(true);
		    for (RadioButton lvlRb : levelOptions_rbList)
		    {
			lvlRb.setVisible(false);
			lvlRb.setDisable(true);
		    }
		}
	    }
	};
    }

    private void init_levelSelector()
    {
	levelOptions_rbList = new ArrayList<RadioButton>();

	for (int i = 0; i < LEVEL.length; i++)
	{
	    RadioButton rb = new RadioButton(String.valueOf(LEVEL[i]).trim()); // make sure that level 
	    levelOptions_rbList.add(rb);
	    rb.setToggleGroup(levelSelector);
	    rb.getStyleClass().add(RADIO_BUTTON_CSS_CLASSNAME);
	}
    }

}
