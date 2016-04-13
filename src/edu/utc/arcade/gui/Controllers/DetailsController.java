package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.gui.DetailsScene;
import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.logging.Log;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Various on 4/4/2016.
 */
public class DetailsController {
    @FXML
    private Button backButton;
    @FXML
    private Button play;
    @FXML
    private Button update;
    @FXML
    private Button install;

    private static final String BACK = "backButton";
    private static final String PLAY = "play";
    private static final String UPDATE = "update";
    private static final String INSTALL = "install";

    public void handleSubmitButtonAction(ActionEvent event) {
        if (!(event.getSource() instanceof Button))
            return;

        final Stage myDialog = new Stage();
        myDialog.initModality(Modality.WINDOW_MODAL);

            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");

            yesButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    myDialog.close();
                }

            });

            noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {myDialog.close();}
            });

            Scene myDialogScene = new Scene(VBoxBuilder.create()
                    .children(new Text("WAIT! Are you sure you want to do this?."), yesButton, noButton)
                    .alignment(Pos.CENTER)
                    .build());

            myDialog.setScene(myDialogScene);
            myDialog.show();


    Button source = (Button) event.getSource();

    Game game = DetailsScene.getInstance().getGame();

    switch(source.getId())

    {
        case BACK:
            backClick();
            break;
        case PLAY:
            playClick();
            break;
        case UPDATE:
            updateClick();
            break;
        case INSTALL:
            installClick();
            break;
    }

}

    @FXML
    public void onKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE)
            backClick();
    }

    private void backClick() {
        UIMain.showBrowseGamesScene();
    }

    private void updateClick() {
        DetailsScene.getInstance().getGame().update();
    }

    private void playClick() {
        try {
            DetailsScene.getInstance().getGame().launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void installClick() {
        DetailsScene scene = DetailsScene.getInstance();
        Game game = scene.getGame();
        scene.setInstallDisable(true);
        if (game.isLocal())
            if (game.uninstall())
                scene.setGame(game);
            else Log.i("Fail");
        else if (game.install())
            scene.setGame(game);
        scene.setInstallDisable(false);
    }
}
