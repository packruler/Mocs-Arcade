package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Various on 4/4/2016.
 */

public class DetailsScene {
    private static DetailsScene INSTANCE;
    private Scene scene;
    private Label titleField;
    private Label developerField;
    private Label descriptionField;


    public static DetailsScene getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DetailsScene();
        return INSTANCE;
    }

    public void setGame(Game game){
        titleField.setText(game.getTitle());
        developerField.setText(game.getDeveloper());
        descriptionField.setText(game.getDescription());
    }

    //TODO make these rows

    public static Scene getScene() {
        return getInstance().scene;
    }

    private DetailsScene() {
        try {
            Pane mainMenuPane = FXMLLoader.load(getClass().getResource("views/DetailsView.fxml"));
            scene = new Scene(mainMenuPane);
            scene.getStylesheets().add(UIMain.class.getResource("css/DetailsView.css").toExternalForm());
            titleField = (Label) scene.lookup("#titleField");
            developerField = (Label) scene.lookup("#developerField");
            descriptionField = (Label) scene.lookup("#descriptionField");
        } catch (IOException e) {
//            Log.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
