package edu.utc.arcade.gui.controllers;

import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;

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
        Log.i(event.getSource().toString());
        if (event.getSource() == homeButton)
            backClick();
        else if (event.getSource() == submit)
            submit();
    }

    @FXML
    public void onKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case ESCAPE:
                backClick();
                break;
            case ENTER:
                submit();
                break;
        }
    }

    private void backClick() {
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
