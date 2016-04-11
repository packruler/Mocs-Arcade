package edu.utc.arcade;

import com.google.gson.Gson;
import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.LaunchInfo;
import edu.utc.arcade.game.OSCheck;
import edu.utc.arcade.game.format.Format;
import edu.utc.arcade.logging.Log;

/**
 * This class is intended to make it easier for game developers to create properly formatted JSON Strings to be used
 * with this library
 * Created by Ethan Leisinger on 4/6/16.
 */
public class GameStringSetup {
    public static void main(String[] args) {
        //If you need to edit a Game JSON String you can place it in "oldGameJsonString"
        Gson gson = new Gson();
        String oldGameJsonString = null;
        Game game = null;
        LaunchInfo launchInfo = null;
        if (oldGameJsonString != null)
            game = gson.fromJson(oldGameJsonString, Game.class);
        else
            game = new Game();

        //Comment out any lines that you do not want to change if the data is loaded from a previous JSON String

        game.setTitle("Hockey");
        game.setDeveloper("Packruler");
        game.setGitAddress("http://github.com/packruler/");
        game.setGitBranch("BRANCH");
        game.setDescription("This is an example of how a game that is not installed will be displayed");
        game.setShortDescription("SHORT DESCRIPTION");
        game.setDataUpdateTime(System.currentTimeMillis());

        //Set the size of following array to number of Operating Systems supported then set the values of the Array to
        //to the Operating Systems and the architecture it is supported on
        String[] osArray = new String[4];
        osArray[0] = OSCheck.WINDOWS + "." + OSCheck.AMD64;
        osArray[1] = OSCheck.WINDOWS + "." + OSCheck.I386;
        osArray[2] = OSCheck.LINUX + "." + OSCheck.AMD64;
        osArray[3] = OSCheck.LINUX + "." + OSCheck.I386;
        game.setOperatingSystems(osArray);
        osArray = new String[osArray.length];

        if (game.loadLaunchInfo())
            launchInfo = game.getLaunchInfo();
        else
            launchInfo = new LaunchInfo();

        //Set the format this Game is designed in. The class "Format" has static instances of the supported formats
        launchInfo.setFormat(Format.JAR);

        //Set the path of the executable related to the main directory of the repository
        // Example: <REPO>/out/Game.jar      Enter: "out/Game.jar"

        //Do NOT use the next method if the path is dependant of the OS it is running on
//        launchInfo.setExecutablePath("PATH");

        //If the executable path changes based on the OS it is running on enter the relative paths in to the osArray in
        // the same order as they are in the supported OS array
        // If the executable path is not dependant on the OS comment the following section out
        osArray[0] = "out/Windows/AMD64";
        osArray[1] = "out/Windows/I386";
        osArray[2] = "out/Linux/AMD64";
        osArray[3] = "out/Linux/I386";
        launchInfo.setOsSpecificExecutablePath(osArray);
        osArray = new String[osArray.length];

        //Set the path of the library related to the main directory of the repository
        // Example: <REPO>/libs     Enter: "libs"

        //Do NOT use the next method if the path is dependant of the OS it is running on
//        launchInfo.setLibraryPath("PATH");

        //If the library path changes based on the OS it is running on enter the relative paths in to the osArray in
        // the same order as they are in the supported OS array
        // If the library path is not dependant on the OS comment the following section out
        osArray[0] = "libs/Windows/AMD64";
        osArray[1] = "libs/Windows/I386";
        osArray[2] = "libs/Linux/AMD64";
        osArray[3] = "libs/Linux/I386";
        launchInfo.setOsSpecificLibraryPath(osArray);

        Log.i("Game JSON String:");
        Log.i(gson.toJson(game));

        Log.i("\n");
        Log.i("LaunchInfo JSON String:");
        Log.i(gson.toJson(launchInfo));

        Log.i("\n\n");
        Log.i("Send Game JSON String to Mocs-Arcade maintainer and request your Game be added or updated");
        Log.i("Place your LaunchInfo JSON String in \"launchInfo.json\" in the main directory of the repository branch" +
                " specified for your game");
    }
}
