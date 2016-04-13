package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import static edu.utc.arcade.settings.Settings.isKioskMode;

/**
 * Created by Chris Sims on 3/23/16.
 */

//http://docs.oracle.com/javafx/2/ui_controls/password-field.htm could help with password handling


public class KioskModeViewController {
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button homeButton;
    @FXML
    private Button submit;

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == homeButton)
            homeClick();
        else if (event.getSource() == submit)
            submit();
    }

    private void homeClick() {
        passwordField.setText("");
        if (Settings.isKioskMode())
            UIMain.showBrowseGamesScene();
        else
            UIMain.showMainMenu();
    }

    private void submit() {
        Settings settings = Settings.getInstance();
        if (!isKioskMode()) {
            if (settings.enterKioskMode(passwordField.getText())) {
                UIMain.updateGameDisplayList();
                UIMain.showBrowseGamesScene();
            } else {
                Log.e("Did not enter kiosk mode");
            }
            passwordField.setText("");
        } else {
            if (settings.exitKioskMode(passwordField.getText())) {
                UIMain.updateGameDisplayList();
                UIMain.showMainMenu();
            } else {
                Log.e("Incorrect password");
            }
            passwordField.setText("");
        }

    }
}
