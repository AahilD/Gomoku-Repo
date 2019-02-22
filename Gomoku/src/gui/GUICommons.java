package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public interface GUICommons
{
	 default Label getGameTitle()
	{
		Label gametitle = new Label("Player Registration");
		gametitle.setFont(new Font(20));
		gametitle.setMaxWidth(Double.MAX_VALUE);
		gametitle.setAlignment(Pos.CENTER);
		gametitle.setPadding(defaultPadding());
		return gametitle;
	}
	
	/**
	 * Call this method to get an instance of Insets with default padding
	 * values.
	 * 
	 * @return Insets with a default padding of (10,10,10,10)
	 */
	default Insets defaultPadding()
	{
		return new Insets(10, 10, 10, 10);
	}
}
