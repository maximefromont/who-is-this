package com.app.wit.View;

import com.app.wit.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewMainMenuController implements Initializable {

    //PUBLIC METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        computeMainMenyButtonStyle(_newgame_button);
        computeMainMenyButtonStyle(_exit_button);
    }

    //PROTECTED METHODS
    @FXML
    protected void handleExitButton() {
        System.exit(0);
    }

    @FXML
    protected void handleNewGameButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select your game data file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV File", "*.csv")
        );
        File game_data_file = fileChooser.showOpenDialog(Application.getMainMenuStage());
    }

    //PRIVATE METHODS
    private void computeMainMenyButtonStyle(Button button)
    {
        button.setStyle(IDLE_BUTTON_STYLE);
        button.setOnMouseEntered(e -> button.setStyle(HOVERED_BUTTON_STYLE));
        button.setOnMouseExited(e -> button.setStyle(IDLE_BUTTON_STYLE));
    }

    //PROTECTED ATTRIBUTE
    @FXML
    protected Button _newgame_button;
    @FXML
    protected Button _exit_button;

    //PRIVATE ATTRIBUTES

    //PRIVATE CONSTANTS
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: rgb(255, 253, 247); -fx-border-radius: 5; -fx-background-radius: 5;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: rgb(166, 61, 64); -fx-border-radius: 5; -fx-background-radius: 5;";
}