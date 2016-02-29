package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.logging.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 2/26/16.
 */
public class GameCellData {
    private static final Insets MAIN_PADDING = new Insets(10);
    private static final Insets INTERNAL_PADDING = new Insets(5);

    @FXML
    private HBox mainBox;
    @FXML
    private Label title,developer, downloadStatus, updateStatus;

    public GameCellData() {
        Log.i("New data");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/game_cell.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        mainBox = new HBox();
//        mainBox.setAlignment(Pos.CENTER);
////        mainBox.setFillHeight(true);
//        mainBox.setPadding(MAIN_PADDING);
//
//        VBox titleDev = new VBox();
//        titleDev.setPadding(INTERNAL_PADDING);
//        titleDev.setSpacing(5d);
//        titleDev.setFillWidth(true);
//
//        title.setFont(new Font("Ariel", 30));
////        title.setPadding(INTERNAL_PADDING);
//        developer.setFont(new Font("Ariel", 20));
////        developer.setPadding(INTERNAL_PADDING);
//
//        titleDev.getChildren().add(title);
//        titleDev.getChildren().add(developer);
//
//        mainBox.getChildren().add(titleDev);
//
//        VBox spacer = new VBox();
//        spacer.setMinWidth(50);
//        mainBox.getChildren().add(spacer);
//
//        VBox statusBox = new VBox();
//        statusBox.setSpacing(5d);
//        statusBox.setPadding(INTERNAL_PADDING);
//        updateStatus.setFont(new Font("Ariel", 20));
////        updateStatus.setPadding(INTERNAL_PADDING);
//        downloadStatus.setFont(new Font("Arel", 20));
////        downloadStatus.setPadding(INTERNAL_PADDING);
//        statusBox.getChildren().add(updateStatus);
//        statusBox.getChildren().add(downloadStatus);
//        statusBox.setAlignment(Pos.TOP_RIGHT);
//
//        mainBox.getChildren().add(statusBox);
    }


    public void loadData(Game game) {
        if (game == null)
            return;
        title.setText(game.getTitle());
        developer.setText(game.getDeveloper());

        setStatus(game);
    }

    private void setStatus(Game game) {
        if (!game.isLocal()) {
            updateStatus.setVisible(false);
            downloadStatus.setText("Downloadable");
        } else {
            updateStatus.setVisible(true);
            if (game.needUpdate()) {
                updateStatus.setText("Updates Behind: " + game.countBehind());
            } else
                updateStatus.setText("Up to date");

            downloadStatus.setText("Downloaded");
        }
    }

    public HBox getMainBox() {
        return mainBox;
    }
}
