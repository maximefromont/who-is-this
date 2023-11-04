package com.app.wit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {

    //PUBLIC METHODS
    @Override
    public void start(Stage stage) throws IOException {
        showMainMenuView(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainMenuStage() {
        return _main_menu_stage;
    }

    public static String getLanguage() {
        return _language;
    }

    public static void setGameDataFile(File game_data_file) {
        _game_data_file = game_data_file;
    }

    public static File getGameDataFile() {
        return _game_data_file;
    }

    public static void showGameView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ViewGame.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            _game_stage = new Stage();
            _game_stage.setTitle("Who is this ?");
            _game_stage.setScene(scene);
            _game_stage.setMinHeight(720);
            _game_stage.setMinWidth(1280);
            _game_stage.setX(_main_menu_stage.getX());
            _game_stage.setY(_main_menu_stage.getY());
            _game_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //PRIVATE METHODS
    private static void showMainMenuView(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ViewMainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            _main_menu_stage = stage;
            _main_menu_stage.setTitle("Who is this ?");
            _main_menu_stage.setScene(scene);
            _main_menu_stage.setMinHeight(720);
            _main_menu_stage.setMinWidth(1280);
            _main_menu_stage.centerOnScreen();
            _main_menu_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //PRIVATE ATTRIBUTES
    private static Stage _main_menu_stage = null;
    private static Stage _game_stage      = null;

    private static File  _game_data_file  = null;

    private static String _language       = "fr";

}