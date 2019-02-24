package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public interface GUICommons
{
	static Label windowHeader(String header)
	{
		Label gametitle = new Label(header);
		gametitle.getStyleClass().add("title-head");
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
	static Insets defaultPadding()
	{
		return new Insets(10, 10, 10, 10);
	}
}
