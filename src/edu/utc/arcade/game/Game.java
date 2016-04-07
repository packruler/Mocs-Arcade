package edu.utc.arcade.game;

import com.google.gson.Gson;
import edu.utc.arcade.git.GameGitHandler;
import edu.utc.arcade.logging.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
public class Game implements Comparable {
    //All values will load save and load from JSON using GSON
    private String title = "";
    private String developer = "";
    private String description = "";
    private String shortDescription = "";
    private String gitAddress;
    private String gitBranch;
    private String[] operatingSystems;
    private boolean local;
    private long dataUpdateTime;

    private LaunchInfo launchInfo;

    //TODO: Move all info except how to get the game to another class that can be loaded based on file stored in repo
    public Game() {
    }

    /**
     * Get the title of the Game
     *
     * @return Title of the Game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the Game.
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param title intended title of the Game
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the name of the Developer of the Game
     *
     * @return Name of the Developer of the Game
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * Set name of Developer
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param developer intended name of Developer
     */
    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    /**
     * Get the description of the Game
     *
     * @return Description of the Game
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the Game
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param description description of the game
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the short description of the game
     *
     * @return Short description of the game
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Set the short description of the Game
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param shortDescription Short description of the game
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Get Git address of the Game repo
     *
     * @return Git address of the Game repo
     */
    public String getGitAddress() {
        return gitAddress;
    }

    /**
     * Set Git address of the Game
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param gitAddress Intended Git address
     */
    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    /**
     * Get name of the branch that should be loaded for the Game
     *
     * @return Name of the branch that should be used. Null if default branch should be used
     */
    public String getGitBranch() {
        return gitBranch;
    }

    /**
     * Set the name of the Branch that should be used for this Game
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param gitBranch Intended Branch name
     */
    public void setGitBranch(String gitBranch) {
        this.gitBranch = gitBranch;
    }

    /**
     * Get array of Operating Systems supported by this Game
     *
     * @return Array of Operating Systems supported
     */
    public String[] getOperatingSystems() {
        return operatingSystems;
    }

    /**
     * Set array of Operating Systems supported by this Game
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param operatingSystems Array of Operating Systems supported
     *                         Example unit: 'Windows.amd64'
     *                         Supported OS:
     *                         'Windows', 'Linux', 'Mac OSX'
     *                         Supported Architecture:
     *                         'amd64', 'i386'
     */
    public void setOperatingSystems(String[] operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    /**
     * Is this Game available locally
     *
     * @return True if Game is available locally
     */
    public boolean isLocal() {
        return local;
    }

    /**
     * Set whether or not Game is now available locally
     *
     * @param local True if Game is local
     */
    public void setLocal(boolean local) {
        this.local = local;
    }

    /**
     * To be used to allow locally stored Game information to be updated when information in the library JSON is updated
     *
     * @return Most recent time of this instance data being updated
     */
    public long getDataUpdateTime() {
        return dataUpdateTime;
    }

    /**
     * Set the time of most recent data update of the Game
     *
     * @param dataUpdateTime
     */
    public void setDataUpdateTime(long dataUpdateTime) {
        this.dataUpdateTime = dataUpdateTime;
    }

    /**
     * Update the game metadata information
     *
     * @param game
     * @return
     */
    public boolean updateData(Game game) {
        if (getDataUpdateTime() >= game.getDataUpdateTime())
            return false;

        setDescription(game.getDescription());
        setShortDescription(game.getShortDescription());
        setGitAddress(game.getGitAddress());
        setGitBranch(game.getGitBranch());
        setDataUpdateTime(game.getDataUpdateTime());
        return true;
    }

    /**
     * Load LaunchInfo class data from the game repo
     *
     * @return true if the LaunchInfo was loaded correctly
     */
    public boolean loadLaunchInfo() {
        if (!local)
            return false;

        File infoLocation = new File("local/" + developer + "/" + title + "/launchInfo.json");
        if (!infoLocation.exists())
            return false;
        try {
            FileReader reader = new FileReader(infoLocation);
            launchInfo = new Gson().fromJson(reader, LaunchInfo.class);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            launchInfo = null;
            return false;
        }
    }

    /**
     * Get the LaunchInfo related to this Game
     *
     * @return LaunchInfo related to this Game
     */
    public LaunchInfo getLaunchInfo() {
        return launchInfo;
    }

    public String getLibraryPath() {
        String path = launchInfo.getLibraryPath();
        if (path != null)
            return path;

        String[] pathArray = launchInfo.getOsSpecificLibraryPath();
        if (pathArray != null)
            return pathArray[OSCheck.getProperOsArrayPosition(this)];

        return null;
    }

    public String getExecutablePath() {
        String path = launchInfo.getExecutablePath();
        if (path != null)
            return path;

        String[] pathArray = launchInfo.getOsSpecificExecutablePath();
        if (pathArray != null)
            return pathArray[OSCheck.getProperOsArrayPosition(this)];

        return null;
    }

    /**
     * Get the number of commits the local repo is behind
     *
     * @return
     */
    public int countBehind() {
        return GameGitHandler.countBehind(this);
    }

    public boolean needUpdate() {
        return countBehind() > 0;
    }

    public boolean update() {
        if (!needUpdate())
            return false;

        boolean updatedGit = GameGitHandler.pull(this);
        Log.i("Updated Git? " + updatedGit);
        loadLaunchInfo();
        return updatedGit;
    }

    public String getStatus() {
        if (isLocal())
            if (needUpdate())
                return "Update";
            else
                return "Installed";
        else if (OSCheck.isCompatible(this))
            return "Available";
        else
            return "Not Available";
    }

    @Override
    public String toString() {
        return title + " | " + developer;
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
            return getDeveloper().compareTo(((Game) o).getDeveloper());
        return getTitle().compareTo(((Game) o).getTitle());
    }
}
