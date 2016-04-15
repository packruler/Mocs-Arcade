package edu.utc.arcade.gui.controllers;

import edu.utc.arcade.git.SystemGitUpdater;
import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.gui.utils.ThreadHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class MainMenuViewController {

    @FXML
    private Button browseGamesButton, kioskModeButton, updateButton, exitButton;

    public void handleSubmitButtonAction(ActionEvent event) {

        if (event.getSource() == browseGamesButton)
            UIMain.showBrowseGamesScene();
        else if (event.getSource() == kioskModeButton)
            UIMain.showKioskPasswordScene();
        else if (event.getSource() == updateButton) {
            updateButton.setText("Updating...");
            updateButton.setDisable(true);
            ThreadHandler.run(new Runnable() {
                @Override
                public void run() {
                    SystemGitUpdater.update();

                }
            });
        } else if (event.getSource() == exitButton)
            Platform.exit();
    }

}
