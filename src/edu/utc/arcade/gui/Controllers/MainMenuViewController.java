package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.gui.UIMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class MainMenuViewController {

    @FXML
    private Button BrowseGamesButton, KioskModeButton, SettingsButton, ExitButton, HomeButton;

    public void handleSubmitButtonAction(ActionEvent event) {

        if (event.getSource()==BrowseGamesButton)
            UIMain.setScene(UIMain.browseGamesScene);
        if (event.getSource()==KioskModeButton)
            UIMain.setScene(UIMain.kioskModeScene);


//        if (event.getSource()==SettingsButton)
//            UIMain.setScene(UIMain.SettingsScene);
        if (event.getSource()==ExitButton)
            Platform.exit();
    }

}
