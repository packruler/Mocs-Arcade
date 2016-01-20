package edu.utc.arcade;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.logging.Log;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/11/16.
 */
public class TestGson {
    public static void main(String[] args) {
        GameLibrary library = new GameLibrary();
        for (Game game : library.getLibrary()) {
            if (game.getDeveloper().equals("Packruler")) {
                Log.i("Update: " + GitHandler.countBehind(game));
                Log.i("Updated? " + GitHandler.pull(game));
                break;
            }
        }
        try {
            library.saveGson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
