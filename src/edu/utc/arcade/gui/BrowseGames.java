package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.settings.Settings;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 3/31/16.
 */
public class BrowseGames {
    private static BrowseGames INSTANCE;
    private Scene scene;
    private ObservableList list;

    public static BrowseGames getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BrowseGames();
        return INSTANCE;
    }

    public static Scene getScene() {
        return getInstance().scene;
    }

    public static void updateGamesDisplayed() {
        if (INSTANCE == null) return;

        INSTANCE.list.remove(0, INSTANCE.list.size());
        INSTANCE.list.addAll(GameLibrary.getLibrary());
    }

    private BrowseGames() {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("views/BrowseGamesView.fxml"));
            scene = new Scene(pane);
            scene.getStylesheets().add(UIMain.class.getResource("css/BrowseGamesView.css").toExternalForm());
            TableView tableView = (TableView) pane.lookup("#tableView");
            list = tableView.getItems();
            list.remove(0, list.size());
            list.addAll(GameLibrary.getLibrary());
            tableView.setRowFactory(tv -> {
                TableRow<Game> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && !row.isEmpty())
                        loadDetails(row.getItem());
                });
                return row;
            });
            tableView.setOnKeyReleased(event -> {
                switch (event.getCode()) {
                    case SPACE:
                        loadDetails((Game) tableView.getSelectionModel().getSelectedItem());
                        break;
                    case ESCAPE:
                        if (Settings.isKioskMode())
                            UIMain.showKioskPasswordScene();
                        else
                            UIMain.showMainMenu();
                        break;
                }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDetails(Game game) {
        DetailsScene detailsScene = DetailsScene.getInstance();
        detailsScene.setGame(game);
        UIMain.setScene(DetailsScene.getScene());
    }
}
