package edu.utc.arcade.game;

/**
 * Created by Ethan Leisinger on 3/23/16.
 */
public class LaunchInfo {
    private String executablePath;
    private String libraryPath;
    private String format;
    private String[] osSpecificExecutablePath;
    private String[] osSpecificLibraryPath;

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

    public String getLibraryPath() {
        return libraryPath;
    }

    public void setLibraryPath(String libraryPath) {
        this.libraryPath = libraryPath;
    }

    /**
     * -Unsure if will be used-
     * Will specify the format of a Game ie Jar vs .exe
     *
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * -Unsure if will be used-
     * Will specify the format of a Game ie Jar vs .exe
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }
    /**
     * Get array of Operating System specific executable path.
     * This should be arranged so that required paths are ordered according to the Operating Systems array
     *
     * @return Array of Operating System specific executable paths.
     */
    public String[] getOsSpecificExecutablePath() {
        return osSpecificExecutablePath;
    }

    /**
     * Set array of Operating System specific executable path.
     * <p>
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param osSpecificExecutablePath This should be arranged so that required paths are ordered according to the Operating Systems array
     *                                 If no OS specific executable required use <i>null</i> or "" for that position
     */
    public void setOsSpecificExecutablePath(String[] osSpecificExecutablePath) {
        this.osSpecificExecutablePath = osSpecificExecutablePath;
    }

    /**
     * Get array of Operating System specific library path
     * This should be arranged so that required paths are ordered according to the Operating Systems array
     *
     * @return Array of Operating Systems supported
     */
    public String[] getOsSpecificLibraryPath() {
        return osSpecificLibraryPath;
    }

    /**
     * Set array of Operating System specific library path
     * -Should only be used for easy setup of adding a game to remote library-
     *
     * @param osSpecificLibraryPath This should be arranged so that required paths are ordered according to the Operating Systems array
     *                              If no OS specific library required use <i>null</i> or "" for that position
     */
    public void setOsSpecificLibraryPath(String[] osSpecificLibraryPath) {
        this.osSpecificLibraryPath = osSpecificLibraryPath;
    }
}
