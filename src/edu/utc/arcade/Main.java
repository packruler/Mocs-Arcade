package edu.utc.arcade;

import com.google.gson.JsonObject;
import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.gui.Controllers.BrowseGamesController;
import edu.utc.arcade.logging.Log;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private JsonObject settings;
    private Pane root;
    private ListView<Game> listView;
    private GameLibrary library = new GameLibrary();
    private Game selectedGame;
    private BrowseGamesController browseGamesController = new BrowseGamesController();

    Button btn1, btn2, btn3, btn4, btn5, btn6;
    Scene scene1, scene2;
    Stage stage;
    FlowPane flowpane1, flowpane2;

    @Override
    public void start(Stage mainScreen) throws Exception {

        stage=mainScreen;
        btn1=new Button("Kiosk Mode");
        btn2=new Button("Admin Mode");
        btn3=new Button("Settings");
        btn4=new Button("Exit");
        btn5=new Button("Main Menu");

        btn1.setOnAction(e->handleButtonAction(e));
        btn2.setOnAction(e->handleButtonAction(e));
        btn3.setOnAction(e->handleButtonAction(e));
        btn5.setOnAction(e->handleButtonAction(e));

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        VBox vb=new VBox(btn1, btn2, btn3, btn4, btn5);
        vb.setSpacing(30);
        vb.setPadding(new Insets(250));

        flowpane1=new FlowPane();
        flowpane2=new FlowPane();
        flowpane1.setVgap(10);
        flowpane2.setVgap(10);
        flowpane1.setStyle("-fx-background-image: url('res/backgrounds/mainScreenBG.jpg')");
        flowpane1.getChildren().add(vb);

        scene1=new Scene(flowpane1, 800, 600);
        scene2=new Scene(flowpane2, 800, 600);



        setupLayout();
        loadLibrary(false);
        mainScreen.setX(primaryScreenBounds.getMinX());
        mainScreen.setY(primaryScreenBounds.getMinY());
        mainScreen.setWidth(primaryScreenBounds.getWidth());
        mainScreen.setHeight(primaryScreenBounds.getHeight());
        mainScreen.setTitle("Mocs Arcade");
        mainScreen.setScene(scene1);

        mainScreen.show();
        Log.i("Height: " + (listView != null ? listView.getHeight() : "NULL"));
    }

    private void loadSettings() throws IOException {
        File settingsFile = new File("./settings.json");

        //If settings file does not exist make a new one
        if (!settingsFile.exists())
            Log.i("Settings file created: " + settingsFile.createNewFile());
    }

    private void setupLayout() {
        root = new Pane();
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setPrefWidth(1920);
        browseGamesController.setGames(library.getLibraryList(false));
        root.getChildren().add(browseGamesController.getMainBox());

    }

    private void loadLibrary(boolean localOnly) {
//        ObservableList<Game> observableList = new ObservableListWrapper<Game>(library.getLibraryList(localOnly));
//        listView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() {
//            @Override
//            public ListCell<Game> call(ListView<Game> param) {
//                return new GameListViewCell();
//            }
//        });
//
//        listView.setItems(observableList);
    }
    private void handleButtonAction(ActionEvent e){
        if (e.getSource()==btn5)
            stage.setScene(scene1);
        else
            //TODO: need to make this scene more passable/dynamic
            stage.setScene(new Scene(root, 1980, 1080));
            //stage.setScene(scene2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
