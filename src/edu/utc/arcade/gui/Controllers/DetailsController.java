package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Various on 4/4/2016.
 */
public class DetailsController {
    @FXML
    private Button BackButton;

    public void handleSubmitButtonAction(ActionEvent event) {
        if (event.getSource() == BackButton)
            backClick();
    }

    private void backClick() {
        if (Settings.isKioskMode())
            UIMain.showKioskPasswordScene();
        else
            UIMain.showBrowseGamesScene();
    }
}
