package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public interface GUICommons
{
    final static String TITLE_BAR_NAME = "GROUP22";

    // DEFAULT PADDING
    final static Insets DEFAULT_PADDING = new Insets(10, 10, 10, 10);

    // CSS CLASSNAMES
    final static String HEADER_CLASS = "title-head";
    static final String BOTTOM_PANE_CLASSNAME = "bottom-pane";
    static final String BOTTOM_PANE_LABEL_CLASSNAME = "bottom-pane-labels";
    static final String END_GAME_BUTTON_CLASSNAME = "end-game-button";
    static final String PLAYER_STATS_VBOX_CLASSNAME = "player-stats-vbox";
    static final String PLAYER_STATS_LABELS_CLASSNAME = "player-stats-label";
    static final String ACTIVE_BOARD_SQUARE_CLASSNAME = "active-board-square";
    static final String OCUPIED_BOARD_SQUARE_CLASSNAME = "ocupied-board-square-";
    static final String PLAYER_STATS_HEADER_LABEL_CLASSNAME = "player-stats-header";
    static final String CUSTOM_CSS_FILENAME = "css/player-registration.css";
    static final String PR_FORMGRID_CLASSNAME = "registration-form";
    static final String FORM_LABEL_CSS_CLASSNAME = "form-label";
    static final String BUTTON_CSS_CLASSNAME = "start-game-button";
    static final String RADIO_BUTTON_CSS_CLASSNAME = "radio-button";

    // LABEL VALUES
    static final String GAME_NAME = "Gomoku";
    static final String PLAYER_STATS_HEADER_VALUE = "Player Panel Board";
    static final String ROUND_COUNT_LABEL = "Rounds: ";
    static final String TURN_COUNT_LABEL = "Turns: ";
    static final String GAMEBOARD_GRID_CSS_CLASSNAME = "gridpane-game-board";

    /**
     * @param header
     * @returns an object of type Label containing the title name of the
     *          application
     */
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
