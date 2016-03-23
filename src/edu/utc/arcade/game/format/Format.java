package edu.utc.arcade.game.format;

import edu.utc.arcade.game.Game;

/**
 * Created by Ethan Leisinger on 1/22/16.
 */
public abstract class Format {
    public static final String JAR = "Jar";
    public static final String C = "C";
    public static final String CPP = "C++";


    public static String[] getExecutable(Game game) {
        switch (game.getLaunchInfo().getFormat()) {
            case JAR:
                return Jar.getExecutableString(game);
        }
        return new String[0];
    }
}
