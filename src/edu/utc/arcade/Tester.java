package edu.utc.arcade;

import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.git.SystemGitUpdater;

/**
 * Created by Ethan Leisinger on 1/11/16.
 */
public class Tester {
    public static void main(String[] args) {
        GameLibrary library = GameLibrary.getInstance();
        SystemGitUpdater.update();

//        Log.i("Is supported? " + ControllerEnvironment.getDefaultEnvironment().isSupported());
//        net.java.games.input.Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
//        Settings settings = Settings.getInstance();
//        assert (settings != null);
//        String pass = "TEST";
//        if (settings.isKioskMode())
//            Log.i("Exit with \"" + pass + "\"? " + settings.exitKioskMode(pass));
//        else Log.i("Set Kiosk Mode Password: \"" + pass + "\": " + settings.enterKioskMode(pass));

//        Log.i("Count system behind: " + SystemGitUpdater.countBehind());

//        for (Game game : library.getLibrary()) {
//            Log.i(game.toString());
//            if (game.getDeveloper().equals("Packruler")) {
//                Log.i("Update: " + game.needUpdate());
//                Log.i("Updated: " + game.update());
//
//                Log.i("Is compatible? " + OSCheck.isCompatible(game));
//                Log.i("Updated? " + GameGitHandler.pull(game));
//                try {
//                    Process process = GameLauncher.LAUNCH(game);
//                    process.waitFor();
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//                break;
//            }
//        }
//        try {
//            library.saveGson();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
