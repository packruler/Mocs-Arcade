package edu.utc.arcade.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 3/31/16.
 */
public class MainMenu {
    private static MainMenu INSTANCE;
    private Scene scene;

    public static MainMenu getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MainMenu();
        return INSTANCE;
    }

    public static Scene getScene() {
        return getInstance().scene;
    }

    private MainMenu() {
        try {
            Pane mainMenuPane = FXMLLoader.load(getClass().getResource("views/MainMenuView.fxml"));
            scene = new Scene(mainMenuPane);
            scene.getStylesheets().add(UIMain.class.getResource("css/MainMenuView.css").toExternalForm());
        } catch (IOException e) {
//            Log.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
