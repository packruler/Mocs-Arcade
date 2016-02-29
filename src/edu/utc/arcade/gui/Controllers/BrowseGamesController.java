package edu.utc.arcade.gui.Controllers;

        import edu.utc.arcade.game.Game;
        import edu.utc.arcade.gui.GameListViewCell;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.*;
        import javafx.scene.control.*;
        import javafx.util.Callback;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;
        import java.util.Set;



public class BrowseGamesController
{
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private ListView listView;
    private Set<String> stringSet;
    ObservableList observableList = FXCollections.observableArrayList();

    public void BrowseGamesController()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layouts/BrowseGamesView.fxml"));
        fxmlLoader.setController(this);
        try
        {
            Parent parent = (Parent)fxmlLoader.load();
            Scene scene = new Scene(parent, 400.0 ,500.0);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setListView()
    {
        stringSet.add("String 1");
        stringSet.add("String 2");
        stringSet.add("String 3");
        stringSet.add("String 4");
        observableList.setAll(stringSet);
        listView.setItems(observableList);
        listView.setCellFactory(new Callback<ListView<Game>, javafx.scene.control.ListCell<Game>>()
        {
            @Override
            public ListCell<Game> call(ListView<Game> listView)
            {
                return new GameListViewCell();
            }
        });
    }

    //TODO: i have to figure out how to initialize these resources and URL

    @FXML
    void initialize(URL location, ResourceBundle resources) {
        System.out.println("BrowseGamesView loaded.");
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'CustomList.fxml'.";

        setListView();
    }
}