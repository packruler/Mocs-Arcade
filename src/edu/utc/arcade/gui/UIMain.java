package edu.utc.arcade.gui;

import edu.utc.arcade.game.GameLibrary;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class UIMain extends Application {

    public static Pane BrowseGamesPane, MainMenuPane, SettingsPane, KioskModePane;
    private static Scene mainMenuScene, browseGamesScene, kioskModeScene;
    private static ObservableList GAME_DISPLAY_LIST;
    private static Stage PRIMARY;

    //    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {

        PRIMARY = primaryStage;

        //browseGamesScene=new Scene(browseGamesPane, 800, 600);


        primaryStage.setTitle("Mocs Arcade Cabinet");
        //this builds the browse games screen below, this needs to be called when the browsegames button is pushed
        //can set it up here, but set the scene when the button is pushed

        //TODO: add back button to screens

        Pane mainMenuPane = (Pane) FXMLLoader.load(getClass().getResource("views/MainMenuView.fxml"));
        Pane browseGamesPane = (Pane) FXMLLoader.load(getClass().getResource("views/BrowseGamesView.fxml"));
        GAME_DISPLAY_LIST = ((javafx.scene.control.TableView) browseGamesPane.getChildren().get(2)).getItems();
        updateGameDisplayList();
        //Pane SettingsPane = (Pane) FXMLLoader.load(getClass().getResource("views/SettingsView.fxml"));
        Pane kioskModePane = (Pane) FXMLLoader.load(getClass().getResource("views/KioskModeView.fxml"));

        mainMenuScene = new Scene(mainMenuPane);
        mainMenuScene.getStylesheets().add(UIMain.class.getResource("css/MainMenuView.css").toExternalForm());
        browseGamesScene = new Scene(browseGamesPane);
        browseGamesScene.getStylesheets().add(UIMain.class.getResource("css/BrowseGamesView.css").toExternalForm());
        kioskModeScene = new Scene(kioskModePane);
        kioskModeScene.getStylesheets().add(UIMain.class.getResource("css/KioskModeView.css").toExternalForm());
        //SettingsScene = new Scene(SettingsPane);

        primaryStage.setHeight(1000);
        primaryStage.setWidth(1000);

        primaryStage.setScene(mainMenuScene);
        //primaryStage.setFullScreen(true);

        primaryStage.show();
    }

    public static void updateGameDisplayList() {
        GAME_DISPLAY_LIST.remove(0, GAME_DISPLAY_LIST.size());
        GAME_DISPLAY_LIST.addAll(GameLibrary.getLibrary());
    }

    public static void setScene(Scene newScene) {

        PRIMARY.setScene(newScene);
        //PRIMARY.setFullScreen(true);
    }

    public static void showBrowseGamesScene() {
        setScene(browseGamesScene);
    }

    public static void showMainMenu() {
        setScene(mainMenuScene);
    }

    public static void showKioskPasswordScene() {
        setScene(kioskModeScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
