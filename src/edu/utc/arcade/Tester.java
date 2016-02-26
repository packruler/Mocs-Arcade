package edu.utc.arcade;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.GameLauncher;
import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.game.OSCheck;
import edu.utc.arcade.git.GameGitHandler;
import edu.utc.arcade.git.SystemGitUpdater;
import edu.utc.arcade.logging.Log;
import net.java.games.input.*;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/11/16.
 */
public class Tester {
    public static void main(String[] args) {
        GameLibrary library = new GameLibrary();
        net.java.games.input.Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        Log.i("Number of devices " + controllers.length);
        Log.i("Count system behind: " + SystemGitUpdater.countBehind());
        for (Game game : library.getLibrary()) {
            if (game.getDeveloper().equals("Packruler")) {
                Log.i("Update: " + game.needUpdate());
                Log.i("Updated: " + game.update());

                Log.i("Is compatible? " + OSCheck.IS_COMPATIBLE(game));
                Log.i("Updated? " + GameGitHandler.pull(game));
                try {
                    Process process = GameLauncher.LAUNCH(game);
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
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
