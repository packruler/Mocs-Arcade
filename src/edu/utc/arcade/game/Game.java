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

    public boolean getLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
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
            return getDeveloper().compareTo(((Game) o).getDeveloper());
        return getTitle().compareTo(((Game) o).getTitle());
    }
}
