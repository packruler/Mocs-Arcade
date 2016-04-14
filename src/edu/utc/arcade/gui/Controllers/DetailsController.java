package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.gui.DetailsScene;
import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.gui.VerifyDialog;
import edu.utc.arcade.gui.utils.ThreadHandler;
import edu.utc.arcade.logging.Log;
import javafx.application.Platform;
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

        switch (source.getId())

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
        update.setText("Updating...");
        update.setDisable(true);
        ThreadHandler.run(new Runnable() {
            @Override
            public void run() {
                final DetailsScene scene = DetailsScene.getInstance();
                final Game game = scene.getGame();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setGame(game);
                    }
                });
            }
        });
    }

    private void playClick() {
        try {
            DetailsScene.getInstance().getGame().launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void installClick() {
        final DetailsScene scene = DetailsScene.getInstance();
        Game game = scene.getGame();
        if (game.isLocal())
            uninstall(game);
        else
            install(game);
    }

    private void install(final Game game) {
        final DetailsScene scene = DetailsScene.getInstance();
        install.setText("Installing...");
        install.setDisable(true);
        ThreadHandler.run(new Runnable() {
            @Override
            public void run() {
                game.install();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        scene.setGame(game);
                    }
                });
            }
        });
    }

    private void uninstall(final Game game) {
        VerifyDialog.display(new VerifyDialog.VerifyListener() {
            @Override
            public void onDismiss(boolean allowed) {
                Log.i("onDismiss: " + allowed);
                if (!allowed)
                    return;

                final DetailsScene scene = DetailsScene.getInstance();
                install.setText("Uninstalling...");
                install.setDisable(true);
                ThreadHandler.run(new Runnable() {
                    @Override
                    public void run() {
                        game.uninstall();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                scene.setGame(game);
                            }
                        });
                    }
                });
            }
        });
    }
}
