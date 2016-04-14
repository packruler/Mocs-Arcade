package edu.utc.arcade.gui;

import edu.utc.arcade.gui.utils.ThreadHandler;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class UIMain extends Application {
    private static Stage PRIMARY;

    //    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        long startTime = System.currentTimeMillis();
        PRIMARY = primaryStage;


        primaryStage.setTitle("Mocs Arcade Cabinet");

        primaryStage.setHeight(720);
        primaryStage.setWidth(1280);

        primaryStage.show();
        Log.i("Startup time took: " + (System.currentTimeMillis() - startTime) + " ms");

        //Preload the unnecessary  scenes after the stage is loaded
        startTime = System.currentTimeMillis();

        Settings.getInstance();

        if (Settings.isKioskMode())
            showBrowseGamesScene();
        else
            showMainMenu();

        ThreadHandler.run(new Runnable() {
            @Override
            public void run() {
                KioskMode.getInstance();
            }
        });

        ThreadHandler.run(new Runnable() {
            @Override
            public void run() {
                if (Settings.isKioskMode())
                    MainMenu.getInstance();
                else
                    BrowseGames.getInstance();
            }
        });

        ThreadHandler.run(new Runnable() {
            @Override
            public void run() {
                DetailsScene.getInstance();
            }
        });

        Log.i("Preload time took: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static void updateGameDisplayList() {
        BrowseGames.updateGamesDisplayed();
    }

    public static void setScene(Scene newScene) {
        PRIMARY.setScene(newScene);
//        PRIMARY.setFullScreen(true);
    }

    public static void showBrowseGamesScene() {
        setScene(BrowseGames.getScene());
    }

    public static void showMainMenu() {
        setScene(MainMenu.getScene());
    }

    public static void showKioskPasswordScene() {
        setScene(KioskMode.getScene());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
