package com.app.wit.View;

import com.app.wit.Application;
import com.app.wit.Tool.PropertiesReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewMainMenuController implements Initializable {

    //PUBLIC METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //BOTH SCENES
        _title_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "game-title"));

        //MAIN MENU SCENE
        computeMainMenyButtonStyle(_newgame_button);
        computeMainMenyButtonStyle(_exit_button);
        _newgame_button.setText(PropertiesReader.getMessage(Application.getLanguage(), "new-game"));
        _exit_button.setText(PropertiesReader.getMessage(Application.getLanguage(), "exit-game"));

        //LOADING SCENE
        _loading_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "loading"));
        setLoadingScreenState(false);
    }

    //PROTECTED METHODS
    @FXML
    protected void handleExitButton() {
        System.exit(0);
    }

    @FXML
    protected void handleNewGameButton() {

        File choosen_file = Application.openFileChooser("select-file", "csv-file", "*.csv", "last-game-file-path");

        if(choosen_file != null) {
            Application.setGameDataFile(choosen_file);
            setLoadingScreenState(true);
            Application.switchToGameView();
        }
    }

    //PRIVATE METHODS
    private void computeMainMenyButtonStyle(Button button)
    {
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
    }

    private void setLoadingScreenState(boolean state) {
        _loading_hbox.setVisible(state);
        _loading_hbox.setManaged(state);

        _buttons_layout_gridpane.setVisible(!state);
        _buttons_layout_gridpane.setManaged(!state);
    }

    //PROTECTED ATTRIBUTE
    @FXML
    protected Label    _title_label;
    @FXML
    protected GridPane _buttons_layout_gridpane;
    @FXML
    protected Button   _newgame_button;
    @FXML
    protected Button   _exit_button;
    @FXML
    protected Label    _loading_label;
    @FXML
    protected HBox     _loading_hbox;

    //PRIVATE ATTRIBUTES

    //PRIVATE CONSTANTS
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: rgb(255, 253, 247); -fx-border-radius: 5; -fx-background-radius: 5;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: rgb(166, 61, 64); -fx-border-radius: 5; -fx-background-radius: 5;";
}