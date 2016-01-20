package edu.utc.arcade.game;

/**
 * Created by Ethan Leisinger on 1/20/16.
 */
public class OSCheck {
    public static final String WINDOWS = "Windows";
    public static final String LINUX = "Linux";
    public static final String MAC_OSX = "Mac OSX";
    public static final String AMD64 = "amd64";
    public static final String I386 = "i386";

    /**
     * Check if Game supports current system
     *
     * @param game Game to check for support of current system
     * @return True if and only if Game supports current OS and Architecture
     */
    public static boolean IS_COMPATIBLE(Game game) {
        String[] osArray = game.getOperatingSystems();
        String os = System.getProperty("os.name");

        //If the OS name contains Windows make it just Windows for ease of search
        if (os.contains(WINDOWS))
            os = WINDOWS;
        String arch = System.getProperty("os.arch");

        for (String cur : osArray) {
            //If current supported OS in array contains the System OS name and System architecture current system is supported
            if (cur.contains(os) && cur.contains(arch))
                return true;
        }
        return false;
    }
}
