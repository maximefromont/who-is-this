package com.app.wit.View;

import com.app.wit.Application;
import com.app.wit.Tool.PropertiesReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class ViewMainMenuController implements Initializable {

    //PUBLIC METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        computeMainMenyButtonStyle(_newgame_button);
        computeMainMenyButtonStyle(_exit_button);
        _newgame_button.setText(PropertiesReader.getMessage(Application.getLanguage(), "new-game"));
        _exit_button.setText(PropertiesReader.getMessage(Application.getLanguage(), "exit-game"));
        _title_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "game-title"));
    }

    //PROTECTED METHODS
    @FXML
    protected void handleExitButton() {
        System.exit(0);
    }

    @FXML
    protected void handleNewGameButton() {

        //Let the user select a file and import it
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(PropertiesReader.getMessage(Application.getLanguage(), "select-file"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(PropertiesReader.getMessage(Application.getLanguage(), "csv-file"), "*.csv")
        );

        //Test if saved directory exists
        File file_to_test = new File(PropertiesReader.getPath("last-game-file-path"));
        if(!file_to_test.exists()) {
            PropertiesReader.setPathValueFromKey("last-game-file-path", "");
        } else {
            fileChooser.setInitialDirectory(file_to_test);
        }

        File choosen_file = fileChooser.showOpenDialog(Application.getMainMenuStage());

        if(choosen_file != null) {
            PropertiesReader.setPathValueFromKey("last-game-file-path", choosen_file.getParent());
            Application.setGameDataFile(choosen_file);

            //Create and show the game window
            Application.getMainMenuStage().hide();
            Application.showGameView();
        }
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
    @FXML
    protected Label _title_label;

    //PRIVATE ATTRIBUTES

    //PRIVATE CONSTANTS
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: rgb(255, 253, 247); -fx-border-radius: 5; -fx-background-radius: 5;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: rgb(166, 61, 64); -fx-border-radius: 5; -fx-background-radius: 5;";
}