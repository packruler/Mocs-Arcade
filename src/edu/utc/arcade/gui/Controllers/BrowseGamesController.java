package edu.utc.arcade.gui.Controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import edu.utc.arcade.game.Game;
import edu.utc.arcade.gui.GameListViewCell;
import edu.utc.arcade.logging.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;


public class BrowseGamesController {
    //    @FXML private ResourceBundle resources;
//    @FXML private URL location;
    @FXML
    private VBox mainBox;
    @FXML
    private ListView browseGamesView;
    @FXML
    private Label title;

    public BrowseGamesController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/BrowseGamesView.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
            setListView();
            title.setFont(new Font(30));
            title.setText("Browse Games");
            browseGamesView.setPrefWidth(1920);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setListView() {
        browseGamesView.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                return new GameListViewCell();
            }
        });
    }

    public void setGames(List<Game> games) {
        browseGamesView.setItems(new ObservableListWrapper<Game>(games));
        Log.i("Size of games list: " + browseGamesView.getItems().size());
    }

    public Node getMainBox() {

        return mainBox;
    }
}