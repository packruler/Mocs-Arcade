package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Created by Ethan Leisinger on 2/26/16.
 */
public class GameInformation {
    private static final Font TITLE_FONT = new Font("Ariel", 30);
    private static final Font DEVELOPER_FONT = new Font("Ariel", 25);
    private static final Font DESCRIPTION_FONT = new Font("Ariel", 20);
    private static final Font BUTTON_FONT = new Font("Ariel", 25);

    public static Node load(Game game, ScrollPane parent, Node previous) {
        VBox root = new VBox();

        Label title = new Label(game.getTitle());
        title.setFont(TITLE_FONT);
        root.getChildren().add(title);

        Label dev = new Label(game.getDeveloper());
        dev.setFont(DEVELOPER_FONT);
        root.getChildren().add(dev);

        Label description = new Label(game.getDescription());
        description.setFont(DESCRIPTION_FONT);
        root.getChildren().add(description);

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        Button download = new Button("Download");
        buttons.getChildren().add(download);
        root.getChildren().add(buttons);

        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ESCAPE))
                    parent.setContent(previous);
            }
        });

        return root;
    }
}
