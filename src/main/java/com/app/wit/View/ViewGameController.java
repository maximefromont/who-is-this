package com.app.wit.View;

import com.app.wit.Application;
import com.app.wit.Model.Character;
import com.app.wit.Tool.PropertiesReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewGameController implements Initializable {

    //PUBLIC METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _title_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "game-title"));
        _characters_list = Character.loadCharactersFromFile(Application.getGameDataFile());
    }

    //PRIVATE ATTRIBUTES
    @FXML
    GridPane _game_layout_grid_pan;
    @FXML
    Label _title_label;

    ArrayList<Character> _characters_list;

}
