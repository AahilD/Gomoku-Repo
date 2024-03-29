package gui;

import java.util.ArrayList;

import controller.PVEnvironment;
import controller.PVPlayer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

// TODO @Aahil finish fixing the styling for Player Registration
// if level 2 has you allready too busy, please let me know I will take this on over the weekend.

/**
 * This class is the main application launcher. This GUI will prompt
 * user to select either player versus player mode (PVP), or, player
 * versus environment mode (PVE). The user(s) will be prompted to enter
 * a user name. Start Game button will open the MainGUI to start
 * playing.
 *         
 * @author GROUP 22
 * @author Emmanuel
 * 
 * 
 */
public class PlayerRegistration extends Application implements GUICommons
{
    // WINDOW DIMENTIONS
    private static double WIDTH = Screen.getPrimary().getBounds().getWidth()
	    / 2;
    private static double HEIGHT = Screen.getPrimary().getBounds().getHeight()
	    / 2;

    // MAIN BORDERPANE
    private BorderPane mainBorderPane = new BorderPane();

    // BUTTONS
    private Button startGame = new Button(START_GAME_BUTTON_TEXT_VALUE);
    private HBox actionButtonContainer = new HBox();

    // CSS FILE NAME
    private static final String CUSTOM_CSS_FILENAME = "player-registration.css";
    // CSS CLASSNAMES
    private static final String ACTION_BUTTON_CONTAINER_CSS_CLASSNAME = "action-button-hbox";
    private static final String PLAYER_REGISTRATION_FORM_GRIDPANE_CSS_CLASSNAME = "registration-form-gridpane";

    private static final String USERNAME_TEXTINPUT_PLACEHOLDER_VALUE = "Enter username: ";
    private static final String START_GAME_BUTTON_TEXT_VALUE = "Start Game";
    private static final String PLAYER_REGISTRATION_HEADER_TEXT_VALUE = "Player Registration";
    private static final String PVPLAYER_LABEL_TEXT_VALUE = "PvP";
    private static final String PVENVIRONMENT_LABEL_TEXT_VALUE = "PvE";

    // Level values in int[]
    // as you add levels include them in this array
    private static final int[] LEVEL = { 0, 1, 2 };

    // Player Registration form grid pane
    private static GridPane regForm_gridPane = new GridPane();

    // labels
    private static final Label ENTER_UNAME_LABEL1 = new Label(
	    USERNAME_TEXTINPUT_PLACEHOLDER_VALUE);
    private static final Label ENTER_UNAME_LABEL2 = new Label(
	    USERNAME_TEXTINPUT_PLACEHOLDER_VALUE);
    private static final Label LEVEL_SELECTION_LABEL = new Label("Level: ");

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
    
    private PlayerRegistrationToggleListeners toggleListener = new PlayerRegistrationToggleListeners();

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
	primaryStage.setTitle(GUICommons.TITLE_BAR_NAME);
	primaryStage.setScene(scene);
	primaryStage.show();

	GUICommons.applyCSS(scene, CUSTOM_CSS_FILENAME);
	addCSSclassNameToNodes();

	primaryStage.setMinWidth(WIDTH);
	primaryStage.setMinHeight(HEIGHT);
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

	// TOP: account information
	mainBorderPane.setTop(
		GUICommons.windowHeader(PLAYER_REGISTRATION_HEADER_TEXT_VALUE));
	// CENTER: Transaction form
	setupPlayerInfoForm();
	mainBorderPane.setCenter(regForm_gridPane);
	mainBorderPane.setRight(getEnvironmentLevelSelectionToggles());

	// BOTTOM: start game button
	setupActionBar();
	mainBorderPane.setBottom(actionButtonContainer);

	HBox emptyspace = new HBox();
	/*emptyspace.setMinWidth(
		GUICommons.getResourceImage("illegal-move-icon.png").getWidth()
			/ 2);*/
	emptyspace.setMinWidth(400);
	mainBorderPane.setLeft(emptyspace);

	return mainBorderPane;
    }

    private Node getEnvironmentLevelSelectionToggles()
    {
	// level selector
	init_levelSelector();

	VBox levelSelectionVbox = new VBox();
	levelSelectionVbox.getChildren().add(LEVEL_SELECTION_LABEL);
	levelSelectionVbox.getChildren().addAll(levelOptions_rbList);
	levelSelectionVbox.getStyleClass().add("lvl-selection-container");

	return levelSelectionVbox;
    }

    /**
     * This method creates a button that will notify the controller to start the
     * game, sending the textfields values as the usernames for each player.
     * 
     * @return Node of HBox containing the button to start the game.
     */
    private void setupActionBar()
    {

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
		    RadioButton lvl = (RadioButton) levelSelector
			    .getSelectedToggle();
		    PVEnvironment.initializeGame(PLAYER_ONE_USERNAME.getText(),
			    Integer.parseInt(lvl.getText()));

		} else
		{
		    System.out.println(
			    "Something went wrong with the radio buttons");
		}

	    }
	});

	actionButtonContainer.getChildren().add(startGame);
    }

    /**
     * This method is in charge of setting up the player registration form.
     * where players will be able to enter their username. Each item is placed
     * inside a GridPane to manage the alignment.
     * 
     * @return Node of type GridPane containing user registration form
     */
    private void setupPlayerInfoForm()
    {
	init_opponentMode();

	// add radioButtons to hbox
	HBox opponentModeModeHbox = new HBox();
	opponentModeModeHbox.getChildren().addAll(playerVplayerRB,
		playerVEnvironmentRB);

	// add all nodes to grid pane

	GridPane.setColumnSpan(opponentModeModeHbox, 2); // span both col
	GridPane.setRowIndex(opponentModeModeHbox, 0);// row 0
	GridPane.setConstraints(ENTER_UNAME_LABEL1, 0, 1); // col=0 row 2
	GridPane.setConstraints(PLAYER_ONE_USERNAME, 1, 1); // col=1 row 2
	GridPane.setConstraints(ENTER_UNAME_LABEL2, 0, 2); // col=0 row 3
	GridPane.setConstraints(PLAYER_TWO_USERNAME, 1, 2); // col=1 row 3

	// add children nodes
	regForm_gridPane.getChildren().addAll(opponentModeModeHbox,
		ENTER_UNAME_LABEL1, PLAYER_ONE_USERNAME, ENTER_UNAME_LABEL2,
		PLAYER_TWO_USERNAME);

	// Style grid pane

	opponentMode.selectedToggleProperty().addListener(toggleListener.getPlayerMode_ToggleListener());
    }

    private void init_opponentMode()
    {
	// Radio buttons
	// RB group
	opponentMode = new ToggleGroup();

	// RB 1: PvP
	playerVplayerRB.setToggleGroup(opponentMode); // add to toggle group

	// RB 2: PvE
	playerVEnvironmentRB.setToggleGroup(opponentMode); // add to toggle
							   // group

	// default selection
	playerVplayerRB.setSelected(true);
    }

    /**
     * Call this method to initialize the level selector. level selector is a
     * toggle group of toggle radio buttons that allow the user to only select a
     * single option.
     * 
     * Warning: As levels are added one should make sure to add it in LEVEL
     * variable and the number of the level should be in the order of its
     * intensity relative to others. Also, make sure the PVE controller has the
     * ability to perform the respective level number.
     * 
     */
    private void init_levelSelector()
    {
	levelOptions_rbList = new ArrayList<RadioButton>();
	levelSelector = new ToggleGroup();

	for (int i = 0; i < LEVEL.length; i++)
	{
	    RadioButton rb = new RadioButton(String.valueOf(LEVEL[i]).trim());
	    levelOptions_rbList.add(rb);

	    if (i == 0)
		rb.setSelected(true);

	    rb.setDisable(true);

	    levelOptions_rbList.get(i).setToggleGroup(levelSelector);
	}
    }

    /**
     * Call this method to add all the css class names to all the nodes
     */
    private void addCSSclassNameToNodes()
    {
	mainBorderPane.getCenter().getStyleClass().add(PR_FORMGRID_CLASSNAME);
	startGame.getStyleClass().add(BUTTON_CSS_CLASSNAME);
	actionButtonContainer.getStyleClass()
		.add(ACTION_BUTTON_CONTAINER_CSS_CLASSNAME);
	playerVplayerRB.getStyleClass().add(RADIO_BUTTON_CSS_CLASSNAME);
	playerVEnvironmentRB.getStyleClass().add(RADIO_BUTTON_CSS_CLASSNAME);
	ENTER_UNAME_LABEL1.getStyleClass().add(FORM_LABEL_CSS_CLASSNAME);
	ENTER_UNAME_LABEL2.getStyleClass().add(FORM_LABEL_CSS_CLASSNAME);
	LEVEL_SELECTION_LABEL.getStyleClass().add(FORM_LABEL_CSS_CLASSNAME);
	levelOptions_rbList.forEach(
		rb -> rb.getStyleClass().add(RADIO_BUTTON_CSS_CLASSNAME));
	regForm_gridPane.getStyleClass()
		.add(PLAYER_REGISTRATION_FORM_GRIDPANE_CSS_CLASSNAME);
    }

    public class PlayerRegistrationToggleListeners
    {
	/**
	 * Call this method to get the toggle listener for the versus mode (pve
	 * || pvp radio butons).
	 * 
	 * @return ChangeListener<? super Toggle>
	 */
	private ChangeListener<? super Toggle> getPlayerMode_ToggleListener()
	{
	    return new ChangeListener<Toggle>()
	    {

		@Override
		public void changed(
			ObservableValue<? extends Toggle> observable,
			Toggle oldValue, Toggle newValue)
		{
		    RadioButton modeRb = (RadioButton) opponentMode
			    .getSelectedToggle();

		    // if the user selected single player mode than hide
		    // the option to enter the player 2 user name
		    if (modeRb.getText().equals(PVENVIRONMENT_LABEL_TEXT_VALUE))
		    {
			// single player mode (toggle dissable properties)
			ENTER_UNAME_LABEL2.setDisable(true);
			PLAYER_TWO_USERNAME.setDisable(true);
			PLAYER_TWO_USERNAME.setText("Big Blue");
			ENTER_UNAME_LABEL2.setText("Computer: ");

			LEVEL_SELECTION_LABEL.setDisable(false);
			for (RadioButton lvlRb : levelOptions_rbList)
			    lvlRb.setDisable(false);

		    } else if (modeRb.getText()
			    .contentEquals(PVPLAYER_LABEL_TEXT_VALUE))
		    {
			// 2 payer mode toggle disable properties
			ENTER_UNAME_LABEL2.setDisable(false);
			PLAYER_TWO_USERNAME.setDisable(false);
			PLAYER_TWO_USERNAME.setText("Player 2");
			ENTER_UNAME_LABEL2
				.setText(USERNAME_TEXTINPUT_PLACEHOLDER_VALUE);

			for (RadioButton lvlRb : levelOptions_rbList)
			    lvlRb.setDisable(true);
		    }
		}
	    };
	}
    }

}
