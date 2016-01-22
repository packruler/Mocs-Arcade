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
    public static long LAUNCH(Game game) {
        String[] cmdArray = Format.getExecutable(game);
        Log.i(Arrays.toString(cmdArray));
        long runTime = System.currentTimeMillis();
        try {
            Process process = Runtime.getRuntime().exec(cmdArray);

            InputStream outputStream = null, errorStream = null;
            ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();
            ByteArrayOutputStream errorBuffer = new ByteArrayOutputStream();
            try {
                outputStream = process.getInputStream();
                errorStream = process.getErrorStream();

                byte[] tmp = new byte[1024];

                while (true) {
                    int outputBytes = readAvailablOnce(outputStream, outputBuffer, tmp);
                    int errorBytes = readAvailablOnce(errorStream, errorBuffer, tmp);
                    if (outputBytes == 0 && errorBytes == 0) {
                        try {
                            process.exitValue();
                            break;
                        } catch (IllegalThreadStateException e) {
                            // keep on looping
                        }
                    }
                }
                readAvailableAll(outputStream, outputBuffer, tmp);
                readAvailableAll(errorStream, errorBuffer, tmp);

            } finally {
                closeQuietly(outputStream);
                closeQuietly(errorStream);
            }

            runTime = System.currentTimeMillis() - runTime;
            Log.i("Runtime: " + runTime);
            System.out.println(outputBuffer.toString("ASCII"));
            System.err.println(errorBuffer.toString("ASCII"));
            System.err.println("exit code: " + process.exitValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return runTime;
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

    private static int readAvailablOnce(
            InputStream inputStream, OutputStream outputStream, byte[] buffer)
            throws IOException {
        int bytesRead = 0;
        if (inputStream.available() > 0) {
            bytesRead = inputStream.read(buffer);
            outputStream.write(buffer, 0, bytesRead);
        }
        return bytesRead;
    }

    private static void readAvailableAll(
            InputStream inputStream, OutputStream outputStream, byte[] buffer)
            throws IOException {
        if (inputStream.available() > 0) {
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
