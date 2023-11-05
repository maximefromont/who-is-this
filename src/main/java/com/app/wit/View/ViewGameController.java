package com.app.wit.View;

import com.app.wit.Application;
import com.app.wit.Model.Character;
import com.app.wit.Tool.PropertiesReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewGameController implements Initializable {

    //PUBLIC METHODS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //_title_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "game-title"));
        _characters_list = Character.loadCharactersFromFile(Application.getGameDataFile());
        _characters_image_list = new ArrayList<ImageView>();
        for(Character character : _characters_list) {
            Image image = new Image(character.getImagePath());
            ImageView image_view = new ImageView();
            image_view.setImage(image);
            image_view.setFitWidth(PICTURE_SIZE);
            image_view.setFitHeight(PICTURE_SIZE);
            image_view.setPreserveRatio(true);
            image_view.setSmooth(true);
            image_view.setCache(true);
            _characters_image_list.add(image_view);
        }

        refeshGameLayout(Application.INITIAL_WINDOW_WIDTH);

        //INITIALIZE THE QUESTIONS TREE
        TreeItem<String> rootItem = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-list"));

        TreeItem<String> question_1 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-1"));
        TreeItem<String> question_1_1 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-1-1"));
        TreeItem<String> question_1_2 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-1-2"));
        TreeItem<String> question_1_3 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-1-3"));
        TreeItem<String> question_1_4 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-1-4"));
        TreeItem<String> question_1_5 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-1-5"));
        question_1.getChildren().addAll(question_1_1, question_1_2, question_1_3, question_1_4, question_1_5);

        TreeItem<String> question_2 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2"));
        TreeItem<String> question_2_1 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-1"));
        TreeItem<String> question_2_2 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-2"));
        TreeItem<String> question_2_3 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-3"));
        TreeItem<String> question_2_4 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-4"));
        TreeItem<String> question_2_5 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-5"));
        TreeItem<String> question_2_6 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-6"));
        TreeItem<String> question_2_7 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-2-7"));
        question_2.getChildren().addAll(question_2_1, question_2_2, question_2_3, question_2_4, question_2_5, question_2_6, question_2_7);

        TreeItem<String> question_3 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-3"));
        TreeItem<String> question_3_1 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-3-1"));
        TreeItem<String> question_3_2 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-3-2"));
        TreeItem<String> question_3_3 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-3-3"));
        TreeItem<String> question_3_4 = new TreeItem<>(PropertiesReader.getMessage(Application.getLanguage(), "question-3-4"));
        question_3.getChildren().addAll(question_3_1, question_3_2, question_3_3, question_3_4);

        TreeItem<String> directory2 = new TreeItem<>("Détails physiques");
        TreeItem<String> file5 = new TreeItem<>("La personne porte elle des lunettes ?");
        TreeItem<String> file6 = new TreeItem<>("La personne a-t-elle une moustache ?");
        directory2.getChildren().addAll(file5, file6);

        rootItem.getChildren().addAll(question_1, question_2, question_3);

        _questions_treeview.setRoot(rootItem);

        //_instruction_label.setText(PropertiesReader.getMessage(Application.getLanguage(), "instruction-pick-character"));
    }

    //PRIVATE METHODS
    public void refeshGameLayout(int window_width) {
        if(_game_layout_grid_pane.getChildren().size() > 0) {
            _game_layout_grid_pane.getChildren().clear();
        }

        int column_counter = 0;
        int row_counter = 0;
        int fraction = (int) Math.floor(window_width / (PICTURE_SIZE + PICTURE_PADDING)) - 3;
        for(ImageView image_view : _characters_image_list) {
            if(column_counter > fraction) { //Picture padding is in the gridpane settings
                column_counter = 0;
                row_counter++;
            }

            // Create a StackPane to center the ImageView within the cell
            StackPane cellPane = new StackPane();
            cellPane.setAlignment(Pos.CENTER);
            cellPane.setStyle(IDLE_BUTTON_STYLE);
            cellPane.setOnMouseEntered(e -> cellPane.setStyle(HOVERED_BUTTON_STYLE));
            cellPane.setOnMouseExited(e -> cellPane.setStyle(IDLE_BUTTON_STYLE));
            cellPane.setPadding(new javafx.geometry.Insets(5, 5, 5, 5)); //This padding has nothing to do with the gridpane padding FYI
            cellPane.getChildren().add(image_view);

            _game_layout_grid_pane.add(cellPane, column_counter, row_counter);
            column_counter++;
        }
    }

    //PRIVATE ATTRIBUTES
    @FXML
    protected GridPane _game_layout_grid_pane;
    @FXML
    protected TreeView<String> _questions_treeview;
    @FXML
    protected HBox _data_form_hbox;
    @FXML
    protected Button _validate_button;
    @FXML
    protected Label _question_title_label;
    @FXML
    protected Label _question_label;
    @FXML
    protected Label _answer_title_label;
    @FXML
    protected Label _answer_label;
    /*
    @FXML
    private Label _title_label;
    @FXML
    private Label _instruction_label;
     */

    private ArrayList<ImageView> _characters_image_list;

    private ArrayList<Character> _characters_list;

    //PRIVATE CONSTANTS
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: #fffdf7; -fx-background-radius: 5;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color: rgb(166, 61, 64); -fx-background-radius: 5;";
    private static final int PICTURE_SIZE = 100;
    private static final int PICTURE_PADDING = 5;

}
