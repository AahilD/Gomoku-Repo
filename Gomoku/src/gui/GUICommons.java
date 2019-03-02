package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

public interface GUICommons
{
	final static String TITLE_BAR_NAME = "GROUP22";

	// DEFAULT PADDING
	final static Insets DEFAULT_PADDING = new Insets(10, 10, 10, 10);

	// CSS CLASS
	final static String HEADER_CLASS = "title-head";

	static Label windowHeader(String header)
	{
		Label gametitle = new Label(header);

		gametitle.getStyleClass().add(HEADER_CLASS);
		gametitle.setMaxWidth(Double.MAX_VALUE);
		gametitle.setAlignment(Pos.CENTER);
		gametitle.setPadding(DEFAULT_PADDING);
		return gametitle;
	}
}
