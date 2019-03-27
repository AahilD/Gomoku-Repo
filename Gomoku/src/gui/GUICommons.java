package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Screen;

public interface GUICommons
{
    static final String TITLE_BAR_NAME = "GROUP22";

    // CSS CLASSNAMES
    static final String HEADER_CLASS = "title-head";
    static final String BOTTOM_PANE_CLASSNAME = "bottom-pane";
    static final String BOTTOM_PANE_LABEL_CLASSNAME = "bottom-pane-labels";
    static final String END_GAME_BUTTON_CLASSNAME = "end-game-button";
    static final String PLAYER_STATS_VBOX_CLASSNAME = "player-stats-vbox";
    static final String PLAYER_STATS_LABELS_CLASSNAME = "player-stats-label";
    static final String ACTIVE_BOARD_SQUARE_CLASSNAME = "active-board-square";
    static final String OCUPIED_BOARD_SQUARE_CLASSNAME = "ocupied-board-square-";
    static final String PLAYER_STATS_HEADER_LABEL_CLASSNAME = "player-stats-header";
    static final String PR_FORMGRID_CLASSNAME = "registration-form";
    static final String FORM_LABEL_CSS_CLASSNAME = "form-label";
    static final String BUTTON_CSS_CLASSNAME = "start-game-button";
    static final String RADIO_BUTTON_CSS_CLASSNAME = "radio-button";
    static final String GAMEBOARD_GRID_CSS_CLASSNAME = "gridpane-game-board";

    // LABEL VALUES
    static final String GAME_NAME = "Gomoku";
    static final String PLAYER_STATS_HEADER_VALUE = "Player Panel Board";
    static final String ROUND_COUNT_LABEL = "Rounds: ";
    static final String TURN_COUNT_LABEL = "Turns: ";

    /**
     * @param header
     * @returns an object of type Label containing the title name of the
     *          application
     */
    public static Label windowHeader(String header)
    {
	Label gametitle = new Label(header);

	gametitle.getStyleClass().add(HEADER_CLASS);
	gametitle.setMaxWidth(Double.MAX_VALUE);
	return gametitle;
    }
    
    public static void applyCSS(Scene scene, String filename)
    {
	File f = getCSSFile(filename);
	try
	{
	    scene.getStylesheets()
		    .add("file:///" + f.getAbsolutePath().replace("\\", "/"));

	} catch (Exception e)
	{
	    System.out.println("Could not load css file.");
	}
    }
    
    public static File getCSSFile(String filename)
    {
	return new File("res/css/" + filename);
    }
}
