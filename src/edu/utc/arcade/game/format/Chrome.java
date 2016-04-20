package edu.utc.arcade.game.format;

import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.OSCheck;

import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 4/19/16.
 */
public class Chrome {
    public static String[] getExecutableString(Game game) {
        LinkedList<String> cmdList = new LinkedList<>();
        String URL = game.getGitAddress();

        if (OSCheck.getCurrentOS().equals(OSCheck.LINUX)) {
            cmdList.add("google-chrome");
            cmdList.add("--app=" + URL);
        } else if (OSCheck.getCurrentOS().equals(OSCheck.MAC_OSX)) {
            cmdList.add("/Applications/Google\\ Chrome.app/Contents/MacOS/Google\\ Chrome");
            cmdList.add("--kiosk");
            cmdList.add(URL);
        } else if (OSCheck.getCurrentOS().equals(OSCheck.WINDOWS)) {
            cmdList.add("\"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"");
            cmdList.add("-kiosk");
            cmdList.add(URL);
        }

        String[] cmdArray = new String[cmdList.size()];
        return cmdList.toArray(cmdArray);
    }
}
