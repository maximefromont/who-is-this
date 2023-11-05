package com.app.wit;

import com.app.wit.Tool.PropertiesReader;
import com.app.wit.View.ViewGameController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
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

    public static String getLanguage() {
        return _language;
    }

    public static void setGameDataFile(File game_data_file) {
        _game_data_file = game_data_file;
    }

    public static File getGameDataFile() {
        return _game_data_file;
    }

    public static void showMainMenuView(Stage stage) {
        try {
            if(_main_menu_stage != null) {
                closeMainMenuView();
            }
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ViewMainMenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            _main_menu_stage = stage;
            _main_menu_stage.setTitle(PropertiesReader.getMessage(_language, "game-title"));
            _main_menu_stage.setScene(scene);
            _main_menu_stage.setMinHeight(720);
            _main_menu_stage.setMinWidth(1280);
            _main_menu_stage.centerOnScreen();
            _main_menu_stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToGameView() { //ShowGameView also close the main view by default, so it's more of a "switch to game" function
        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ViewGame.fxml"));
                Parent root = (Parent)fxmlLoader.load();
                Scene scene = new Scene(root);
                _game_stage = new Stage();
                _game_stage.setTitle(PropertiesReader.getMessage(_language, "game-title"));
                _game_stage.setScene(scene);
                _game_stage.setMinHeight(720);
                _game_stage.setMinWidth(1280);
                _game_stage.setX(_main_menu_stage.getX());
                _game_stage.setY(_main_menu_stage.getY());

                _game_stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                    ViewGameController controller = (ViewGameController)fxmlLoader.getController();
                    controller.refeshGameLayout(newVal.intValue());
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

            Application.closeMainMenuView();
            _game_stage.show();
        });
    }

    public static void closeMainMenuView() {
        _main_menu_stage.close();
        _main_menu_stage = null;
    }

    public static File openFileChooser(String title_key, String extension_help_key, String extension, String path_key) {
        //Let the user select a file and import it
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(PropertiesReader.getMessage(Application.getLanguage(), title_key));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        PropertiesReader.getMessage(Application.getLanguage(), extension_help_key), extension));

        //Test if saved directory exists
        File file_to_test = new File(PropertiesReader.getPath(path_key));
        if(!file_to_test.exists()) {
            PropertiesReader.setPathValueFromKey(path_key, "");
        } else {
            fileChooser.setInitialDirectory(file_to_test);
        }

        File choosen_file = fileChooser.showOpenDialog(_main_menu_stage);

        if(choosen_file != null) {
            PropertiesReader.setPathValueFromKey(path_key, choosen_file.getParent());
        }

        return choosen_file;
    }

    //PUBLIC CONSTANTS
    public static final int INITIAL_WINDOW_WIDTH = 1280;
    public static final int INITIAL_WINDOW_HEIGHT = 720;
    public static final int PICTURE_SIZE = 100;
    public static final int PICTURE_PADDING = 5;

    //PRIVATE ATTRIBUTES
    private static Stage _main_menu_stage = null;
    private static Stage _game_stage      = null;

    private static File  _game_data_file  = null;

    private static String _language       = "fr";

}