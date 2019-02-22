package gui;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
	 */
	public void mainwindow(Stage primaryStage)
	{
		Scene scene = new Scene(stageGUI());
		primaryStage.minWidthProperty().bind(scene.widthProperty());
		primaryStage.minHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle("GROUP22");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Parent stageGUI()
	{
		// use borderpanes for the main layout
		BorderPane borderpane = new BorderPane();
		
		// TOP: account information
		borderpane.setTop(getGameTitle());
		
		// CENTER: Transaction form
		borderpane.setCenter(setupPlayerInfoForm());
		
		// BOTTOM: start game button
		borderpane.setBottom(startGameButton());
		
		// LEFT: Nothing
		// RIGHT: Nothing
		
		return borderpane;
	}
}
