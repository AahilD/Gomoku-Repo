package gui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class will contain all the dialogs/alerts for the controller to
 * display to the user as required.
 *         
 * @author Emmanuel
 * 
 */
public class AlertsAndDialogs implements GUICommons
{
    /**
     * The controller may call this method to warn the user of an illegal move.
     * This method takes in a String that represents all the information that
     * will be displayed to the user in the Dialog box.
     * 
     * @param message
     */
    public void dipslay_illegalMoveAlert(String message)
    {
	Alert alert = new Alert(AlertType.WARNING);

	alert.initStyle(StageStyle.UNDECORATED);

	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

	stage.getIcons()
		.add(GUICommons.getResourceImage("illegal-move-icon.png"));
	alert.getDialogPane().setGraphic(new ImageView(
		GUICommons.getResourceImage("illegal-move-icon.png")));
	GUICommons.applyCSS(alert.getDialogPane().getScene(),
		"alertsAndDialogs.css");

	alert.setHeaderText(message);

	ButtonType ok = new ButtonType("Ok");
	alert.getButtonTypes().set(0, ok);

	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ok)
	{
	    alert.close();
	}
    }

    /**
     * Call this method once victory has been signaled by the controller. Takes
     * in a String containing any addition information the controller may want
     * to communicate to user. The string containing the question that will ask
     * the user if they wish to play again will automatically be appended to the
     * message string.
     * <p>
     * </p>
     * If the User selects: Yes it will return true, if the user does not select
     * yes it will return false.
     * 
     * @param wnlMsg Is a string that contains any addition information to display
     *           	 to the user in the alert. The request to play an other round
     *           	 is automatically appended on a new line to the message String.
     */
    public boolean display_newRoundConfirmationAlert(String wnlMsg)
    {
	Alert alert = new Alert(AlertType.CONFIRMATION);

	alert.initStyle(StageStyle.UNDECORATED);

	Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

	stage.getIcons().add(GUICommons.getResourceImage("win-icon.png"));
	alert.getDialogPane().setGraphic(
		new ImageView(GUICommons.getResourceImage("win-icon.png")));
	GUICommons.applyCSS(alert.getDialogPane().getScene(),
		"alertsAndDialogs.css");

	alert.setHeaderText(
		wnlMsg + "\n" + "Do you wish to play an other round?");

	ButtonType yes = new ButtonType("Yes");
	ButtonType no = new ButtonType("No");

	alert.getButtonTypes().setAll(yes, no);

	Button noBT = (Button) alert.getDialogPane()
		.lookupButton(alert.getButtonTypes().get(1));
	noBT.getStyleClass().add("no-button");

	Optional<ButtonType> result = alert.showAndWait();
	return result.get() == yes;
    }
}
