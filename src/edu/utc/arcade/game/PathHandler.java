package edu.utc.arcade.game;

/**
 * Created by Ethan Leisinger on 1/27/16.
 */
public class PathHandler {
    public static String GET_EXECUTABLE_PATH(Game game) {
        int pos = OSCheck.getProperOsArrayPosition(game);
        if (pos == -1)
            throw new RuntimeException("Game not supported by this OS");

        if (game.getLaunchInfo().getOsSpecificExecutablePath()[pos] != null
                && !game.getLaunchInfo().getOsSpecificExecutablePath()[pos].isEmpty())
            return game.getLaunchInfo().getOsSpecificExecutablePath()[pos];

        return game.getLaunchInfo().getExecutablePath();
    }

    public static String GET_LIBRARY_PATH(Game game) {
        int pos = OSCheck.getProperOsArrayPosition(game);
        if (pos == -1)
            throw new RuntimeException("Game not supported by this OS");

        if (game.getLaunchInfo().getOsSpecificLibraryPath()[pos] != null
                && !game.getLaunchInfo().getOsSpecificLibraryPath()[pos].isEmpty())
            return game.getLaunchInfo().getOsSpecificLibraryPath()[pos];

        return game.getLaunchInfo().getExecutablePath();
    }
}
