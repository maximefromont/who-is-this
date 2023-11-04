package com.app.wit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    //PUBLIC METHODS
    @Override
    public void start(Stage stage) throws IOException {
        showMainMenu(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getMainMenuStage() {
        return _main_menu_stage;
    }

    //PRIVATE METHODS
    private void showMainMenu(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ViewMainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        _main_menu_stage = stage;
        _main_menu_stage.setTitle("Who is this ?");
        _main_menu_stage.setScene(scene);
        _main_menu_stage.show();
    }

    //PRIVATE ATTRIBUTES
    private static Stage _main_menu_stage = null;
}