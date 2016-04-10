package edu.utc.arcade.game.format;

import edu.utc.arcade.game.Game;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by packr on 4/10/2016.
 */
public class SimpleRun {
    public static String[] getExecutableString(Game game) {
        LinkedList<String> cmdList = new LinkedList<>();
        File gameDir = new File("local/" + game.getDeveloper() + "/" + game.getTitle());
        cmdList.add(gameDir.getAbsolutePath() + "/" + game.getExecutablePath());
        String[] cmdArray = new String[cmdList.size()];
        return cmdList.toArray(cmdArray);
    }
}
