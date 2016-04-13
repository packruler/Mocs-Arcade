package edu.utc.arcade.gui.Controllers;

import edu.utc.arcade.gui.GameFactory;
import edu.utc.arcade.gui.UIMain;
import edu.utc.arcade.settings.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Chris Sims on 3/23/16.
 */
public class BrowseGamesViewController {
    @FXML
    private TableView<GameFactory> tableView;
    @FXML
    private HBox HomeBox;
    @FXML
    private TextField titleField;
    @FXML
    private TextField developerField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField shortDescriptionField;
    @FXML
    private Button homeButton;

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == homeButton)
            backClick();
    }

    private void backClick() {
        if (Settings.isKioskMode())
            UIMain.showKioskPasswordScene();
        else
            UIMain.showMainMenu();
    }
}