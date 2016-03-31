package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class KioskModeViewController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button HomeButton;
    @FXML
    private Button submit;

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        if (event.getSource() == HomeButton)
            homeClick();
        else if (event.getSource() == submit)
            submit();
    }

    private void homeClick() {
        passwordField.setText("");
        UIMain.setScene(UIMain.mainMenuScene);
    }

    private void submit() {
        Settings settings = Settings.getInstance();
        if (!settings.isKioskMode()) {
            if (settings.enterKioskMode(passwordField.getText())) {
                UIMain.updateGameDisplayList();
                UIMain.setScene(UIMain.browseGamesScene);
            } else {
                Log.e("Did not enter kiosk mode");
            }
            passwordField.setText("");
        } else {
            if (settings.exitKioskMode(passwordField.getText())) {
                UIMain.updateGameDisplayList();
                UIMain.setScene(UIMain.mainMenuScene);
            } else {
                Log.e("Incorrect password");
            }
            passwordField.setText("");
        }

    }
}
