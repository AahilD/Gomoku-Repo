import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * @author GROUP 22
 * 
 *         This class will manage all the front-end graphical user interface for
 *         the Banking application.
 * 
 */
public class BankGUI extends Application
{
	/**
	 * 		This class Handles the ActionEvent from the deposit button by depositing
	 * 		the number in the TextField (converted from a string to a double) into a
	 * 		BankAccount, then updates the labels in the GUI with the new balance
	 */
	class HandleDeposit implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0)
		{
			bankAcc.deposit(Double.valueOf(amountValue.getText().trim()));
			updateBalanceLabel();
		}

	}
	
	/**
	 * 		This class Handles the ActionEvent from the withdrawal button by withdrawing
	 * 		the number in the TextField (converted from a string to a double) from a
	 * 		BankAccount, then updates the labels in the GUI with the new balance
	 */
	class HandleWithdrawal implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent arg0)
		{
			bankAcc.withdraw(Double.valueOf(amountValue.getText().trim()));
			updateBalanceLabel();
		}
		
	}

	// BankAccount Instance Variable
	private BankAccount bankAcc = new BankAccount();
	// input text field for amount value
	private TextField amountValue = new TextField();
	// deposit button
	private Button depositB = new Button("Deposit");
	// withdraw button
	private Button withdrawB = new Button("Withdraw");
	// balance label (value)
	private Label balanceLabel = new Label("Balance: ");

	/**
	 * Main method which will launch the GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage)
	{
		// setup scene and show
		Scene scene = new Scene(stageGUI());
		primaryStage.minWidthProperty().bind(scene.widthProperty());
		primaryStage.minHeightProperty().bind(scene.heightProperty());
		primaryStage.setTitle("My Bank");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Call this method to stage the GUI for a scene. The main layout will use
	 * BorderPane.
	 * 
	 * @return BorderPane
	 */
	private BorderPane stageGUI()
	{
		// use borderpanes for the main layout
		BorderPane borderpane = new BorderPane();

		// TOP: account information
		borderpane.setTop(
				setupAccountInformation(
						"GROUP 22"));

		// CENTER: Transaction form
		borderpane.setCenter(
				setupTransactionForm());

		// BOTTOM: Form action buttons
		borderpane.setBottom(
				setupActionButtonForm());

		// LEFT: Nothing yet
		// RIGHT: Nothing yet

		return borderpane;
	}

	/**
	 * Call this method to display the labels and their corresponding values for
	 * the customer name (account holder name) and account balance, along with
	 * their corresponding labels within a grid.
	 * 
	 * @param customerName of type String; the name of the account holder
	 * @param balance      of type double; the balance currently in the account
	 * @return GridPane that will display these values within a grid
	 */
	private GridPane setupAccountInformation(String customerName)
	{
		balanceLabel.setText(balanceToString());
		
		// Initiate gridpane
		GridPane gridpane = new GridPane();

		// Setup the nodes

		// Set labels
		VBox labelsVbox = new VBox(
				new Label("Account Holder: "),
				new Label("Balance: "));
		// Float right
		labelsVbox.setAlignment(Pos.CENTER_RIGHT);
		
		
		// Set values
		VBox valuesVbox = new VBox(
				new Label(customerName),
				balanceLabel);

		// Place labels in grid
		GridPane.setConstraints(labelsVbox, 0, 0); // Column=0 row=0
		// Place values in grid
		GridPane.setConstraints(valuesVbox, 1, 0); // Column=1 row=0

		// Add all the nodes to gridpane
		gridpane.getChildren().addAll(
				labelsVbox, valuesVbox);

		// align grid to top-center
		gridpane.setAlignment(Pos.TOP_CENTER);

		// add default padding
		gridpane.setPadding(defaultPadding());

		return gridpane;
	}

	/**
	 * Call this method to setup and return the form for the transaction
	 * (excluding the action buttons)
	 * 
	 * @return GridPane of the transaction form
	 */
	private GridPane setupTransactionForm()
	{
		GridPane gridpane = new GridPane();

		// nodes
		Label amountLabel = new Label("Enter Amount: ");

		// label
		GridPane.setConstraints(amountLabel, 0, 0); // col=0 row=0
		// value input
		GridPane.setConstraints(amountValue, 1, 0); // col=1 row=0

		// add nodes to grid
		gridpane.getChildren().addAll(amountLabel, amountValue);

		// align to center-center
		gridpane.setAlignment(Pos.CENTER);

		// add default padding
		gridpane.setPadding(defaultPadding());

		return gridpane;
	}

	/**
	 * Call this method to setup the action buttons related to the transaction
	 * form
	 * 
	 * @return HBox filled with action buttons for the transaction form
	 */
	private HBox setupActionButtonForm()
	{
		
		// setup the action event for the deposit button
		depositB.setOnAction(new HandleDeposit());
		
		// setup the action event for the withdraw button
		withdrawB.setOnAction(new HandleWithdrawal());

		
		// Each button represents an action for the form
		// Place them all in a row horizontally
		HBox hBox = new HBox(depositB, withdrawB);

		// align hbox bottom-center
		hBox.setAlignment(Pos.BOTTOM_CENTER);

		// add default padding
		hBox.setPadding(defaultPadding());

		return hBox;

	}
	
	/**
	 * Call this method to set (update) the text for the balanceLabel.
	 * Uses balanceToString() to setup the balance into a formatted string
	 */
	private void updateBalanceLabel()
	{
		balanceLabel.setText(balanceToString());
	}
	
	/**
	 * this will setup and return a formated string for the current
	 * account balance into the typical format for currency.
	 * (e.g. if balance == 0.0 then it will return "$0.00" as a string)
	 * 
	 * @return the formatted string of the account balance.
	 */
	private String balanceToString()
	{
		return "$" + String.format("%.2f", bankAcc.getBalance());
	}

	/**
	 * Call this method to get an instance of Insets with default padding
	 * values.
	 * 
	 * @return Insets with a default padding of (10,10,10,10)
	 */
	private Insets defaultPadding()
	{
		return new Insets(10, 10, 10, 10);
	}
}
