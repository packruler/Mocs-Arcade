package edu.utc.arcade.game.format;

import edu.utc.arcade.game.Game;

/**
 * Created by Ethan Leisinger on 1/22/16.
 */
public abstract class Format {
    //If the game is a Jar file that has libraries that must be defined at run time use "Jar" as the format
    public static final String JAR = "Jar";
    //In almost all other cases use "Simple Run"
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
