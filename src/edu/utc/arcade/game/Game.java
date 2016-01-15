package edu.utc.arcade.game;


import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;

import javax.print.DocFlavor;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
//TODO Setup what information will be contained in the game class to handle creating each individual game JSON string
public class Game {
    //All values will load save and load from JSON using GSON
    private String title;
    private String developer;
    private String gitAddress;
    private String gitBranch;
    private String localDirectory;
    private String executablePath;
    private String format;
    private String[] operatingSystem;

    public Game() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
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

    public String getExecutablePath() {
        return executablePath;
    }

    public String getFormat() {
        return format;
    }
}
