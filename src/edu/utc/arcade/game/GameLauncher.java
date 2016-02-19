package edu.utc.arcade.game;

import edu.utc.arcade.game.format.Format;
import edu.utc.arcade.logging.Log;

import java.io.*;
import java.util.Arrays;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
public class GameLauncher {

    /**
     * This is used to launch a game
     *
     * @param game The game that should be launched
     * @return The length of time the game ran.
     */
    public static Process LAUNCH(Game game) throws IOException {
        //Use Format class to get a command String array to pass to Runtime.exec()
        String[] cmdArray = Format.getExecutable(game);

        //Launch Game using command array from Format
        ProcessBuilder builder = new ProcessBuilder(cmdArray);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);

        return builder.start();
    }

    private static void closeQuietly(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                // ignored
            }
        }
    }

    //From http://stackoverflow.com/questions/8751651/handling-output-error-of-process-separately-in-java-without-extra-threads
    private static int readAvailablOnce(InputStream inputStream, OutputStream outputStream, byte[] buffer)
            throws IOException {
        int bytesRead = 0;
        if (inputStream.available() > 0) {
            bytesRead = inputStream.read(buffer);
            outputStream.write(buffer, 0, bytesRead);
        }
        return bytesRead;
    }

    //From http://stackoverflow.com/questions/8751651/handling-output-error-of-process-separately-in-java-without-extra-threads
    private static void readAvailableAll(InputStream inputStream, OutputStream outputStream, byte[] buffer)
            throws IOException {
        if (inputStream.available() > 0) {
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
