package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.OSCheck;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Arrays;

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
    private Label supportedSystemsField;
    private Button updateButton;
    private Button playButton;
    private Button installButton;

    private Game game;


    public static DetailsScene getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DetailsScene();
        return INSTANCE;
    }

    public void setGame(Game game) {
        this.game = game;

        titleField.setText(game.getTitle());
        developerField.setText(game.getDeveloper());
        descriptionField.setText(game.getDescription());
        //supportedSystemsField.setText(Arrays.toString(game.getOperatingSystems()));

        if (!game.isLocal()) {
            playButton.setDisable(true);
            updateButton.setDisable(true);
            installButton.setText("Install");
            installButton.setDisable(!OSCheck.isCompatible(game) || Settings.isKioskMode());
        } else {
            playButton.setDisable(false);
            if (!game.needUpdate()) {
                updateButton.setDisable(true);
                updateButton.setText("Up To Date");
            } else {
                updateButton.setDisable(Settings.isKioskMode());
                updateButton.setText("Update");
            }
            installButton.setDisable(Settings.isKioskMode());
            installButton.setText("Uninstall");
            
        }
    }


    public Game getGame() {
        return game;
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
            playButton = (Button) scene.lookup("#play");
            updateButton = (Button) scene.lookup("#update");
            installButton = (Button) scene.lookup("#install");
        } catch (IOException e) {
//            Log.e(e.getMessage());
            e.printStackTrace();
        }
    }

    public void setInstallDisable(boolean disable) {
        installButton.setDisable(disable);
    }
}
