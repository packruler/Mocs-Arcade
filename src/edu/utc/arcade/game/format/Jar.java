package edu.utc.arcade.game.format;

import edu.utc.arcade.game.Game;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 1/22/16.
 */
public class Jar {

    public static String[] getExecutableString(Game game) {
        LinkedList<String> cmdList = new LinkedList<>();
        File gameDir = new File("local/" + game.getDeveloper() + "/" + game.getTitle());
        File libs = new File(gameDir.getPath() + "/" + game.getLibraryPath());
        cmdList.add("java");
        if (game.getLaunchInfo().getLibraryPath() != null)
            cmdList.add("-Djava.library.path=" + libs.getAbsolutePath());

        cmdList.add("-jar");
        cmdList.add(gameDir.getAbsolutePath() + "/" + game.getExecutablePath());
        String[] cmdArray = new String[cmdList.size()];
        return cmdList.toArray(cmdArray);
    }
}
