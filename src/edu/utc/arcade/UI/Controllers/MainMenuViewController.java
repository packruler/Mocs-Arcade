package edu.utc.arcade.UI.Controllers;

import UI.UIMain;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sun.applet.Main;

/**
 * Created by Various on 3/21/2016.
 */
public class MainMenuViewController {

    @FXML
    private Button BrowseGamesButton, KioskModeButton, SettingsButton, ExitButton;

    public void handleSubmitButtonAction(ActionEvent event) {
        if (event.getSource()==BrowseGamesButton)
            UIMain.setScene(UIMain.BrowseGamesScene);
        if (event.getSource()==KioskModeButton)
            UIMain.setScene(UIMain.KioskModeScene);
        if (event.getSource()==SettingsButton)
            UIMain.setScene(UIMain.SettingsScene);
        if (event.getSource()==ExitButton)
            Platform.exit();

    }

}
