package edu.utc.arcade;

import com.google.gson.JsonObject;
import edu.utc.arcade.logging.Log;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private JsonObject settings;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    private void loadSettings() throws IOException {
        File settingsFile=new File("./settings.json");

        //If settings file does not exist make a new one
        if (!settingsFile.exists())
            Log.i("Settings file created: " + settingsFile.createNewFile());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
