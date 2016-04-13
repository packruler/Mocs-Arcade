package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.gui.DetailsScene;
import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.logging.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

        Button source = (Button) event.getSource();

        Game game = DetailsScene.getInstance().getGame();

        switch (source.getId()) {
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
