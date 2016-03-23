package edu.utc.arcade.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UIMain extends Application {

    public static Pane BrowseGamesPane, MainMenuPane, SettingsPane, KioskModePane;
    public static Scene BrowseGamesScene, KioskModeScene, SettingsScene;
    private static Stage PRIMARY;

    //    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        PRIMARY = primaryStage;

        //BrowseGamesScene=new Scene(BrowseGamesPane, 800, 600);


        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Mocs Arcade Cabinet");
        //this builds the browse games screen below, this needs to be called when the browsegames button is pushed
        //can set it up here, but set the scene when the button is pushed

        Pane MainMenuPane = (Pane) FXMLLoader.load(getClass().getResource("../../../../res/Views/MainMenuView.fxml"));
        Pane BrowseGamesPane = (Pane) FXMLLoader.load(getClass().getResource("../../../../res/Views/TableView.fxml"));
        Pane SettingsPane = (Pane) FXMLLoader.load(getClass().getResource("../../../../res/Views/SettingsView.fxml"));
        Pane KioskModePane = (Pane) FXMLLoader.load(getClass().getResource("../../../../res/Views/KioskModeView.fxml"));

        Scene myScene = new Scene(MainMenuPane);
        BrowseGamesScene = new Scene(BrowseGamesPane);
        KioskModeScene = new Scene(KioskModePane);
        SettingsScene = new Scene(SettingsPane);


        primaryStage.setScene(myScene);
        myScene.getStylesheets().add(UIMain.class.getResource("Css/MainMenuView.css").toExternalForm());
        primaryStage.show();
    }

    public static void setScene(Scene newScene) {
        PRIMARY.setScene(newScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
