package edu.utc.arcade.game;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
public class Game implements Comparable {
    //All values will load save and load from JSON using GSON
    private String title = "";
    private String developer = "";
    private String gitAddress;
    private String gitBranch;
    private boolean local;
    private String executablePath;
    private String format;
    private String[] operatingSystem;

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
     * Get the localized path of Game executable
     *
     * @return Path of executable
     */
    public String getExecutablePath() {
        return executablePath;
    }

    /**
     * Set the localized path of Game executable
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param executablePath Localized path to executable
     *                       Example: <i>Repo</i>/build/executable.jar should be build/executable.jar
     */
    public void setExecutablePath(String executablePath) {
        this.executablePath = executablePath;
    }

    /**
     * -Unsure if will be used-
     * Will specify the format of a Game ie Java vs .exe
     *
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * -Unsure if will be used-
     * Will specify the format of a Game ie Java vs .exe
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Get array of Operating Systems supported by this Game
     *
     * @return Array of Operating Systems supported
     */
    public String[] getOperatingSystems() {
        return operatingSystem;
    }

    /**
     * Set array of Operating Systems supported by this Game
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param operatingSystem Array of Operating Systems supported
     */
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
            return getDeveloper().compareTo(((Game) o).getDeveloper());
        return getTitle().compareTo(((Game) o).getTitle());
    }
}
