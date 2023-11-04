package com.app.wit.View;

import com.app.wit.Application;
import com.app.wit.Model.Character;
import com.app.wit.Tool.PropertiesReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewGameController implements Initializable {

    //PUBLIC METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        _title_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "game-title"));
        _characters_list = Character.loadCharactersFromFile(Application.getGameDataFile());

        int column_counter = 0;
        int row_counter = 0;
        for(Character character : _characters_list) {
            if(column_counter > 10) {
                column_counter = 0;
                row_counter++;
            }
            Image image = new Image(character.getImagePath());
            ImageView image_view = new ImageView();
            image_view.setImage(image);
            image_view.setFitWidth(100);
            image_view.setFitHeight(100);
            image_view.setPreserveRatio(true);
            image_view.setSmooth(true);
            image_view.setCache(true);

            // Create a StackPane to center the ImageView within the cell
            StackPane cellPane = new StackPane();
            cellPane.setAlignment(Pos.CENTER);
            cellPane.setStyle("-fx-background-color: #fffdf7;");
            cellPane.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
            cellPane.getChildren().add(image_view);

            _game_layout_grid_pane.add(cellPane, column_counter, row_counter);
            column_counter++;
        }

        _instruction_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "instruction-pick-character"));

    }

    //PRIVATE ATTRIBUTES
    @FXML
    private GridPane _game_layout_grid_pane;
    @FXML
    private Label _title_label;
    @FXML
    private Label _instruction_label;

    private ArrayList<Character> _characters_list;

}
