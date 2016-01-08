package edu.utc.arcade.game;

import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
public class GameLauncher {

    /**
     * This is used to launch a game's exe and makes this system wait until the game's exe is completed(finished)
     * @param file The file that is intended to be executed
     * @return The length of time the game ran.
     */
    public static long LAUNCH(String file) {
        long startTime = System.currentTimeMillis();
        try {
            //Launches the file and waits until the file is closed.
            //TODO Verify this actually works
            Runtime.getRuntime().exec(file).waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis() - startTime;
    }
}
