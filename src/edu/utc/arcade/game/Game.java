package edu.utc.arcade.game;


import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;

import javax.print.DocFlavor;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
//TODO Setup what information will be contained in the game class to handle creating each individual game JSON string
public class Game implements Comparable {
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

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public String getGitBranch() {
        return gitBranch;
    }

    public void setGitBranch(String gitBranch) {
        this.gitBranch = gitBranch;
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

    public void setExecutablePath(String executablePath) {
        this.executablePath = executablePath;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String[] getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String[] operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            return ((Game) obj).getDeveloper().equals(developer)
                    && ((Game) obj).getTitle().equals(title);
        }
        return super.equals(obj);
    }

    @Override
    public int compareTo(Object o) {
        assert o instanceof Game;

        if (((Game) o).getTitle().equals(getTitle()))
            return ((Game) o).getDeveloper().compareTo(getDeveloper());
        return ((Game) o).getTitle().compareTo(getTitle());
    }
}
