package edu.utc.arcade.game;


import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;

import javax.print.DocFlavor;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
//TODO Setup what information will be contained in the game class to handle creating each individual game JSON string
public class Game {
    private static final String TITLE = "Title";
    private static final String DEVELOPER = "Developer";
    private static final String GIT_ADDRESS = "Git Address";
    private static final String LOCAL_DIRECTORY = "Local Directory";
    private static final String EXECUTABLE_PATH = "Excecutable Path";
    private static final String FORMAT = "Format";
    private static final String TIME_PLAYED = "Time Played";
    private static final String RATING_TOTAL = "Rating Total";
    private static final String RATING_COUNT = "Rating Count";

    private String title;
    private String developer;
    private String gitAddress;
    private String localDirectory;
    private String executablePath;
    private String format;
    private long timePlayed;
    private int ratingTotal;
    private int ratingCount;

    public void Game(JsonObject jsonObject) {
        title = jsonObject.get(TITLE).getAsString();
        developer = jsonObject.get(DEVELOPER).getAsString();
        gitAddress = jsonObject.get(GIT_ADDRESS).getAsString();
        localDirectory = jsonObject.get(LOCAL_DIRECTORY).getAsString();
        executablePath = jsonObject.get(EXECUTABLE_PATH).getAsString();
        format = jsonObject.get(FORMAT).getAsString();
        timePlayed = jsonObject.get(TIME_PLAYED).getAsLong();
        ratingTotal = jsonObject.get(RATING_TOTAL).getAsInt();
        ratingCount = jsonObject.get(RATING_COUNT).getAsInt();
    }

    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(TITLE, title);
        jsonObject.addProperty(DEVELOPER, developer);
        jsonObject.addProperty(GIT_ADDRESS, gitAddress);
        jsonObject.addProperty(LOCAL_DIRECTORY, localDirectory);
        jsonObject.addProperty(EXECUTABLE_PATH, executablePath);
        jsonObject.addProperty(FORMAT, format);
        jsonObject.addProperty(TIME_PLAYED, timePlayed);
        jsonObject.addProperty(RATING_TOTAL, ratingTotal);
        jsonObject.addProperty(RATING_COUNT, ratingCount);

        return jsonObject;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public String getLocalDirectory() {
        return localDirectory;
    }

    public void setLocalDirectory(String directory) {
        localDirectory = directory;
    }
}
