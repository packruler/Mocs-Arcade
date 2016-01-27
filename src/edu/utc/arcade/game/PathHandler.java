package edu.utc.arcade.game;

/**
 * Created by Ethan Leisinger on 1/27/16.
 */
public class PathHandler {
    public static String GET_EXECUTABLE_PATH(Game game) {
        int pos = OSCheck.GET_PROPER_OS_ARRAY_POSITION(game);
        if (pos == -1)
            throw new RuntimeException("Game not supported by this OS");

        if (game.getOsSpecificExecutablePath()[pos] != null
                && !game.getOsSpecificExecutablePath()[pos].isEmpty())
            return game.getOsSpecificExecutablePath()[pos];

        return game.getExecutablePath();
    }

    public static String GET_LIBRARY_PATH(Game game) {
        int pos = OSCheck.GET_PROPER_OS_ARRAY_POSITION(game);
        if (pos == -1)
            throw new RuntimeException("Game not supported by this OS");

        if (game.getOsSpecificLibraryPath()[pos] != null
                && !game.getOsSpecificLibraryPath()[pos].isEmpty())
            return game.getOsSpecificLibraryPath()[pos];

        return game.getExecutablePath();
    }
}
