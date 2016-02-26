package edu.utc.arcade.gui;

import edu.utc.arcade.game.Game;
import javafx.scene.control.ListCell;

/**
 * Created by Ethan Leisinger on 2/10/16.
 */
public class GameListViewCell extends ListCell<Game> {

    @Override
    protected void updateItem(Game item, boolean empty) {
        super.updateItem(item, empty);
        if (empty)
            return;

        GameCellData data = new GameCellData();
        data.loadData(item);
        setGraphic(data.getMainBox());
    }
}
