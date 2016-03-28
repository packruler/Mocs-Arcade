package edu.utc.arcade.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.management.monitor.Monitor;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class UIMain extends Application {

    public static Pane BrowseGamesPane, MainMenuPane, SettingsPane, KioskModePane;
    public static Scene MainMenuScene, BrowseGamesScene, KioskModeScene, SettingsScene;
    private static Stage PRIMARY;

    //    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        PRIMARY = primaryStage;

        //BrowseGamesScene=new Scene(BrowseGamesPane, 800, 600);



        primaryStage.setTitle("Mocs Arcade Cabinet");
        //this builds the browse games screen below, this needs to be called when the browsegames button is pushed
        //can set it up here, but set the scene when the button is pushed

        //TODO: add back button to screens

        Pane MainMenuPane = (Pane) FXMLLoader.load(getClass().getResource("views/MainMenuView.fxml"));
        Pane BrowseGamesPane = (Pane) FXMLLoader.load(getClass().getResource("views/BrowseGamesView.fxml"));
        //Pane SettingsPane = (Pane) FXMLLoader.load(getClass().getResource("views/SettingsView.fxml"));
        Pane KioskModePane = (Pane) FXMLLoader.load(getClass().getResource("views/KioskModeView.fxml"));

        MainMenuScene = new Scene(MainMenuPane);
        MainMenuScene.getStylesheets().add(UIMain.class.getResource("css/MainMenuView.css").toExternalForm());
        BrowseGamesScene = new Scene(BrowseGamesPane);
        BrowseGamesScene.getStylesheets().add(UIMain.class.getResource("css/BrowseGamesView.css").toExternalForm());
        KioskModeScene = new Scene(KioskModePane);
        KioskModeScene.getStylesheets().add(UIMain.class.getResource("css/KioskModeView.css").toExternalForm());
        //SettingsScene = new Scene(SettingsPane);

        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        primaryStage.setScene(MainMenuScene);
        //primaryStage.setFullScreen(true);

        primaryStage.show();
    }

    public static void setScene(Scene newScene) {

        PRIMARY.setScene(newScene);
        //PRIMARY.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
