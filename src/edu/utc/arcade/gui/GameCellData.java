package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by Ethan Leisinger on 2/26/16.
 */
public class GameCellData {
    private HBox mainBox;
    private Label title = new Label();
    private Label developer = new Label();
    private Label downloadStatus = new Label();
    private Label updateStatus = new Label();

    public GameCellData() {
        mainBox = new HBox();
        mainBox.setFillHeight(true);
//        mainBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        VBox titleDev = new VBox();
//        titleDev.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        titleDev.setSpacing(5d);
        titleDev.setFillWidth(true);

        title.setFont(new Font("Ariel", 30));
        developer.setFont(new Font("Ariel", 20));

        titleDev.getChildren().add(title);
        titleDev.getChildren().add(developer);

        VBox statusBox = new VBox();
        updateStatus.setFont(new Font("Ariel", 20));
        downloadStatus.setFont(new Font("Arel", 20));
        statusBox.getChildren().add(updateStatus);
        statusBox.getChildren().add(downloadStatus);
        statusBox.setAlignment(Pos.TOP_RIGHT);


        mainBox.getChildren().add(titleDev);
        mainBox.getChildren().add(statusBox);
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
