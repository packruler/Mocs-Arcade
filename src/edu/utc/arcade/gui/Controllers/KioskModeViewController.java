package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.gui.UIMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class KioskModeViewController {
    @FXML
    private Text actiontarget;
    @FXML
    private Button HomeButton;

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        if (event.getSource()==HomeButton)
            UIMain.setScene(UIMain.mainMenuScene);
    }
}
