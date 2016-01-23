package edu.utc.arcade;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.GameLauncher;
import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.game.OSCheck;
import edu.utc.arcade.git.GameGitHandler;
import edu.utc.arcade.git.SystemGitUpdater;
import edu.utc.arcade.logging.Log;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/11/16.
 */
public class Tester {
    public static void main(String[] args) {
        GameLibrary library = new GameLibrary();

        SystemGitUpdater systemGitUpdater = new SystemGitUpdater();
        Log.i("Count system behind: " + systemGitUpdater.countBehind());
        for (Game game : library.getLibrary()) {
            if (game.getDeveloper().equals("Packruler")) {
                Log.i("Update: " + GameGitHandler.countBehind(game));
                Log.i("Is compatible? " + OSCheck.IS_COMPATIBLE(game));
                Log.i("Updated? " + GameGitHandler.pull(game));
                GameLauncher.LAUNCH(game);
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
