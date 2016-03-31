package edu.utc.arcade.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 3/31/16.
 */
public class KioskMode {
    private static KioskMode INSTANCE;
    private Scene scene;

    public static KioskMode getInstance() {
        if (INSTANCE == null)
            INSTANCE = new KioskMode();
        return INSTANCE;
    }

    public static Scene getScene() {
        return getInstance().scene;
    }

    private KioskMode() {
        try {
            Pane KioskModePane = FXMLLoader.load(getClass().getResource("views/KioskModeView.fxml"));
            scene = new Scene(KioskModePane);
            scene.getStylesheets().add(UIMain.class.getResource("css/KioskModeView.css").toExternalForm());
        } catch (IOException e) {
//            Log.e(e.getMessage());
            e.printStackTrace();
        }
    }
}
