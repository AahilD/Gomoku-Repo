package gui;

import controller.GameManager;
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

/**
 * @author GROUP 22
 * @author Emmanuel
 * 
 * This class is the main application launcher.
 * This GUI will prompt user to select either player versus player mode (PVP), or, player versus envrionment mode (PVE).
 * The user(s) will be prompted to enter a user name.
 * Start Game button will open the MainGUI to start playing.
 * 
 */
public class PlayerRegistration extends Application implements GUICommons
{
	// labels
	private static final String USERNAME_LABEL = "Enter username: ";
	private static final String START_GAME_BUTTON_VALUE = "Start Game";
	private static final String PLAYER_REGISTRATION = "Player Registration";

	// The TextField for player 1 user name
	private static TextField PLAYER_ONE_USERNAME = new TextField("Player 1");
	// The TextField for player 2 user name
	private static TextField PLAYER_TWO_USERNAME = new TextField("Player 2");

	// Radio Buttons
	// PLAYER VS PLAYER (2 PLAYER)
	private static final String PVP_LABEL = "PvP";
	RadioButton playerVplayerRB = new RadioButton(PVP_LABEL);

	// PLAYER VS ENVRIONMENT (SINGLE_PLAYER)
	private static final String PVE_LABEL = "PvE";
	private static RadioButton playerVEnvironmentRB = new RadioButton(
			PVE_LABEL);

	private static final String CUSTOM_CSS_FILENAME = "css/player-registration.css";
	private static final String PR_FORMGRID_CLASSNAME = "registration-form";
	
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
		primaryStage.minWidthProperty().bind(scene.widthProperty());
		primaryStage.minHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle(GUICommons.TITLE_BAR_NAME);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		scene.getStylesheets().add(MainGUI.class.getResource(CUSTOM_CSS_FILENAME).toExternalForm());
	}

	/**
	 * Use this method to manage the setup for the main window of the user
	 * registration gui. Currently uses BorderPane for the final window layout.
	 * 
	 * @return Node of type BorderPane composed of various sub-nodes
	 */
	private BorderPane stageGUI()
	{
		// use borderpanes for the main layout
		BorderPane borderpane = new BorderPane();

		// TOP: account information
		borderpane.setTop(GUICommons.windowHeader(PLAYER_REGISTRATION));
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
		Button startGame = new Button(START_GAME_BUTTON_VALUE);
		startGame.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Stage stage = (Stage) startGame.getScene().getWindow();
				stage.close();

				if (playerVplayerRB.isSelected())
					GameManager.initializeGame(PLAYER_ONE_USERNAME.getText(),
							PLAYER_TWO_USERNAME.getText());
				else if (playerVEnvironmentRB.isSelected())
					// TODO once the computer game environment has been
					// implemented
					// change the following to call the appropriate method
					// for now we will just invoke 2player mode
					GameManager.initializeGame(PLAYER_ONE_USERNAME.getText(),
							"COMPUTER (NOT)");
				else
					System.out.println(
							"Something went wrong with the radio buttons");

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
		// Labels for the place holders
		Label enterUsernameLabel1 = new Label(USERNAME_LABEL);
		Label enterUsernameLabel2 = new Label(USERNAME_LABEL);

		// Radio buttons
		// RB group
		ToggleGroup opponentMode = new ToggleGroup();

		// RB 1: PvP

		playerVplayerRB.setToggleGroup(opponentMode);
		playerVplayerRB.setSelected(true);

		// RB 2: PvE

		playerVEnvironmentRB.setToggleGroup(opponentMode);

		// add radioButtons to hbox
		HBox rbhb = new HBox();
		rbhb.getChildren().addAll(playerVplayerRB, playerVEnvironmentRB);

		// Style the nodes
		// radio buttons
		playerVplayerRB.setPadding(DEFAULT_PADDING);
		playerVEnvironmentRB.setPadding(DEFAULT_PADDING);
		rbhb.setPadding(DEFAULT_PADDING);
		rbhb.setMaxWidth(Double.MAX_VALUE);
		rbhb.setAlignment(Pos.CENTER);

		// Labels
		enterUsernameLabel1.setPadding(DEFAULT_PADDING);
		enterUsernameLabel2.setPadding(DEFAULT_PADDING);

		// Text fields
		PLAYER_ONE_USERNAME.setPadding(DEFAULT_PADDING);
		PLAYER_TWO_USERNAME.setPadding(DEFAULT_PADDING);

		// create GridPane
		GridPane form = new GridPane();

		// add all nodes to grid pane
		GridPane.setColumnSpan(rbhb, 2);
		GridPane.setConstraints(enterUsernameLabel1, 0, 1); // col=0 row=0
		GridPane.setConstraints(PLAYER_ONE_USERNAME, 1, 1); // col=1 row=0
		GridPane.setConstraints(enterUsernameLabel2, 0, 2); // col=0 row=1
		GridPane.setConstraints(PLAYER_TWO_USERNAME, 1, 2); // col=1 row=1

		// add children nodes
		form.getChildren().addAll(rbhb, enterUsernameLabel1,
				PLAYER_ONE_USERNAME, enterUsernameLabel2, PLAYER_TWO_USERNAME);

		// Style grid pane
		form.setHgap(10);
		form.setVgap(10);
		form.setPadding(GUICommons.DEFAULT_PADDING);
		form.setMaxWidth(Double.MAX_VALUE);
		form.setAlignment(Pos.CENTER);
		form.setPadding(GUICommons.DEFAULT_PADDING);

		opponentMode.selectedToggleProperty()
				.addListener(new ChangeListener<Toggle>()
				{

					@Override
					public void changed(
							ObservableValue<? extends Toggle> observable,
							Toggle oldValue, Toggle newValue)
					{
						RadioButton rb = (RadioButton) opponentMode
								.getSelectedToggle();

						// if the user selected single player mode than hide
						// the option to enter the player 2 user name
						if (rb.getText().equals(PVE_LABEL))
						{
							enterUsernameLabel2.setVisible(false);
							PLAYER_TWO_USERNAME.setVisible(false);
						} else if (rb.getText().contentEquals(PVP_LABEL))
						{
							enterUsernameLabel2.setVisible(true);
							PLAYER_TWO_USERNAME.setVisible(true);
						}
					}

				});
		
		return form;
	}

}
