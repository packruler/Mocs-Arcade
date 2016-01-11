package edu.utc.arcade;

import com.google.gson.Gson;
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
        Game game = new Game();
        game.setTitle("Test1");
        library.addGame(game);
        game = new Game();
        game.setTitle("Test2");
        library.addGame(game);
        try {
            library.saveGson();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
