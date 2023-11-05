package com.app.wit.Model;

import com.app.wit.Application;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Tile {

    //PUBLIC METHODS
    public Tile(Character character) {

        _character = character;

        Image image = new Image(_character.getImagePath());
        ImageView image_view = new ImageView();
        image_view.setImage(image);
        image_view.setFitWidth(Application.PICTURE_SIZE);
        image_view.setFitHeight(Application.PICTURE_SIZE);
        image_view.setPreserveRatio(true);
        image_view.setSmooth(true);
        image_view.setCache(true);

        _cell_pane = new StackPane();
        _cell_pane.setAlignment(Pos.CENTER);
        _cell_pane.setStyle(IDLE_BUTTON_STYLE);
        _cell_pane.setOnMouseEntered(e -> setHovered(true));
        _cell_pane.setOnMouseExited(e -> setHovered(false));
        _cell_pane.setOnMouseClicked(e -> toggleSelected());
        _cell_pane.setPadding(new javafx.geometry.Insets(5, 5, 5, 5)); //This padding has nothing to do with the gridpane padding FYI
        _cell_pane.getChildren().add(image_view);
    }

    public void setHovered(boolean state) {
        _is_hovered = state;
        refreshStyle();
    }

    public void toggleSelected() {
        if(_is_selected) {
            _is_selected = false;
            _selected_tiles_counter--;
        } else {
            _is_selected = true;
            _selected_tiles_counter++;
        }
        refreshStyle();
    }

    public StackPane getCellPane() {
        return _cell_pane;
    }

    public static int getSelectedTilesCounter() {
        return _selected_tiles_counter;
    }

    //PRIVATE METHODS
    private void refreshStyle() {
        if(_is_selected || _is_hovered) {
            _cell_pane.setStyle(HOVERED_BUTTON_STYLE);
        } else {
            _cell_pane.setStyle(IDLE_BUTTON_STYLE);
        }
    }

    //PRIVATE ATTRIBUTES
    private boolean _is_hovered;
    private boolean _is_selected;
    private StackPane _cell_pane;
    private Character _character;

    //PRIVATE STATIC ATTRIBUTES
    private static int _selected_tiles_counter = 0;

    //PRIVATE CONSTANTS
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #fffdf7; -fx-background-radius: 5;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: rgb(166, 61, 64); -fx-background-radius: 5;";
}
