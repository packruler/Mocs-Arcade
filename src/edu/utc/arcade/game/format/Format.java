package edu.utc.arcade.game.format;

import edu.utc.arcade.game.Game;

/**
 * Created by Ethan Leisinger on 1/22/16.
 */
public abstract class Format {
    public static final String JAR = "Jar";
    public static final String SIMPLE_RUN = "Simple Run";


    public static String[] getExecutable(Game game) {
        switch (game.getLaunchInfo().getFormat()) {
            case JAR:
                return Jar.getExecutableString(game);
            case SIMPLE_RUN:
                return SimpleRun.getExecutableString(game);
        }
        return new String[0];
    }
}
