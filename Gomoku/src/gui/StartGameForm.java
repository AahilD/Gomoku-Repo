package gui;

import controller.GameManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * @author GROUP 22
 *
 *         This class will be a gui instance that will load when the game
 *         initially starts it will prompt for two usernames to be entered upon
 *         clicking the "start game" will send the usernames to the controller
 *         to set up the game and board.
 * 
 */
public class StartGameForm extends Application implements GUICommons
{
	// The TextField for player 1 username
	final TextField PLAYER_ONE_USERNAME = new TextField("Player 1");
	// the TextField for player 2 username
	final TextField PLAYER_TWO_USERNAME = new TextField("Player 2");
	
	// labels
	private static final String USERNAME_LABEL = "Enter username: ";
	private static final String START_GAME_BUTTON_VALUE = "Start Game";
	private static final String PLAYER_REGISTRATION = "Player Registration";

	/**
	 * This is the official main method that will be called to launch 
	 * the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/** 
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * Called by the main method of this class.
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
	}

	/**
	 * Use this method to manage the setup for the main window of the user registration gui.
	 * Currently uses BorderPane for the final window layout.
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

		// BOTTOM: start game button
		borderpane.setBottom(startGameButton());

		// LEFT: Nothing
		// RIGHT: Nothing

		return borderpane;
	}

	/**
	 * This method creates a button that will notify the controller
	 * to start the game, sending the textfields values as the usernames
	 * for each player.
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
				// do what you have to do
				stage.close();
				GameManager.initializeGame(PLAYER_ONE_USERNAME.getText(),
						PLAYER_TWO_USERNAME.getText());
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
		
		// stylize the nodes
		enterUsernameLabel1.setPadding(GUICommons.DEFAULT_PADDING);
		enterUsernameLabel2.setPadding(GUICommons.DEFAULT_PADDING);
		PLAYER_ONE_USERNAME.setPadding(GUICommons.DEFAULT_PADDING);
		PLAYER_TWO_USERNAME.setPadding(GUICommons.DEFAULT_PADDING);
		
		// create GridPane
		GridPane form = new GridPane();
		
		// add all nodes to grid pane
		GridPane.setConstraints(enterUsernameLabel1, 0, 0); // col=0 row=0
		GridPane.setConstraints(PLAYER_ONE_USERNAME, 1, 0); // col=1 row=0
		GridPane.setConstraints(enterUsernameLabel2, 0, 2); // col=0 row=1
		GridPane.setConstraints(PLAYER_TWO_USERNAME, 1, 2); // col=1 row=1
		
		// add children nodes
		form.getChildren().addAll(enterUsernameLabel1, PLAYER_ONE_USERNAME,
				enterUsernameLabel2, PLAYER_TWO_USERNAME);
		
		//stylize gridpane
		form.setHgap(10);
		form.setVgap(10);
		form.setPadding(GUICommons.DEFAULT_PADDING);
		form.setMaxWidth(Double.MAX_VALUE);
		form.setAlignment(Pos.CENTER);
		form.setPadding(GUICommons.DEFAULT_PADDING);
		
		return form;
	}

}
