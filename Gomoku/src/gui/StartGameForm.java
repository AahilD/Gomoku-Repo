package gui;

import controller.GameSetup;
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
	final TextField PLAYER_ONE_USERNAME = new TextField("Player 1");
	final TextField PLAYER_TWO_USERNAME = new TextField("Player 2");

	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage primaryStage)
	{
		//
		Scene scene = new Scene(stageGUI());
		primaryStage.minWidthProperty().bind(scene.widthProperty());
		primaryStage.minHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle("GROUP22");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * This method will set up the overall layout of the form pop up gui.
	 * 
	 * @return BorderPane type layout
	 */
	private BorderPane stageGUI()
	{
		// use borderpanes for the main layout
		BorderPane borderpane = new BorderPane();

		// TOP: account information
		borderpane.setTop(GUICommons.windowHeader("Player Registration"));
		// CENTER: Transaction form
		borderpane.setCenter(setupPlayerInfoForm());

		// BOTTOM: start game button
		borderpane.setBottom(startGameButton());

		// LEFT: Nothing
		// RIGHT: Nothing

		return borderpane;
	}

	private Node startGameButton()
	{
		Button startGame = new Button("Start Game");
		startGame.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Stage stage = (Stage) startGame.getScene().getWindow();
			    // do what you have to do
			    stage.close();
				GameSetup.initializeGame(PLAYER_ONE_USERNAME.getText(),
						PLAYER_TWO_USERNAME.getText());
			}
		});
		HBox box = new HBox(startGame);
		box.setMaxWidth(Double.MAX_VALUE);
		box.setAlignment(Pos.CENTER_RIGHT);
		box.setPadding(GUICommons.defaultPadding());
		return box;
	}

	private Node setupPlayerInfoForm()
	{
		Label enterUsernameLabel1 = new Label("Enter username: ");
		Label enterUsernameLabel2 = new Label("Enter username: ");

		enterUsernameLabel1.setPadding(GUICommons.defaultPadding());
		enterUsernameLabel2.setPadding(GUICommons.defaultPadding());
		PLAYER_ONE_USERNAME.setPadding(GUICommons.defaultPadding());
		PLAYER_TWO_USERNAME.setPadding(GUICommons.defaultPadding());

		GridPane form = new GridPane();

		GridPane.setConstraints(enterUsernameLabel1, 0, 0); // col=0 row=0
		GridPane.setConstraints(PLAYER_ONE_USERNAME, 1, 0); // col=1 row=0
		GridPane.setConstraints(enterUsernameLabel2, 0, 2); // col=0 row=1
		GridPane.setConstraints(PLAYER_TWO_USERNAME, 1, 2); // col=1 row=1

		form.getChildren().addAll(enterUsernameLabel1, PLAYER_ONE_USERNAME,
				enterUsernameLabel2, PLAYER_TWO_USERNAME);

		form.setHgap(10);
		form.setVgap(10);

		form.setPadding(GUICommons.defaultPadding());
		form.setMaxWidth(Double.MAX_VALUE);
		form.setAlignment(Pos.CENTER);
		form.setPadding(GUICommons.defaultPadding());
		return form;
	}

}
