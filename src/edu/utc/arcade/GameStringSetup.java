package edu.utc.arcade;

import com.google.gson.Gson;
import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.LaunchInfo;
import edu.utc.arcade.logging.Log;

import java.util.LinkedList;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        String answer;
//        Log.i("Would you like to modify an existing Game JSON String? (y/n)");
//        String answer = scanner.nextLine();
//        while (!answer.equals("y") && !answer.equals("n")) {
//            Log.i("Please enter \'y\' or \'n\'");
//            answer = scanner.nextLine();
//        }
//        if (answer.equals("y")) {
//            Log.i("Please input the current JSON String");
//            oldGameJsonString = scanner.nextLine();
//            game = gson.fromJson(oldGameJsonString, Game.class);
//        } else
        game = new Game();

        //Comment out any lines that you do not want to change if the data is loaded from a previous JSON String
        Log.i("Enter the title of the game:");
        game.setTitle(scanner.nextLine());
        Log.i("Enter the developer:");
        game.setDeveloper(scanner.nextLine());
        Log.i("Enter the git URL:");
        game.setGitAddress(scanner.nextLine());
        Log.i("Enter the git branch:");
        game.setGitBranch(scanner.nextLine());
        Log.i("Enter the short description of the game:");
        game.setShortDescription(scanner.nextLine());
        Log.i("Enter the full description of the game:");
        game.setDescription(scanner.nextLine());
        game.setDataUpdateTime(System.currentTimeMillis());

        //Set the size of following array to number of Operating Systems supported then set the values of the Array to
        //to the Operating Systems and the architecture it is supported on
        LinkedList<String> osArray = new LinkedList<>();
        Log.i("What is the first operating system supported?");
        osArray.add(scanner.nextLine());
        Log.i("Is there another OS you support?");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            Log.i("Please enter \'y\' or \'n\'");
            answer = scanner.nextLine();
        }
        while (answer.equals("y")) {
            Log.i("Please enter the next operating system supported:");
            osArray.add(scanner.nextLine());
            Log.i("Is there another OS you support?");
            answer = scanner.nextLine();
            while (!answer.equals("y") && !answer.equals("n")) {
                Log.i("Please enter \'y\' or \'n\'");
                answer = scanner.nextLine();
            }
        }


//        Log.i("Would you like to modify an existing LaunchInfo JSON String? (y/n)");
//        answer = scanner.nextLine();
//        while (!answer.equals("y") && !answer.equals("n")) {
//            Log.i("Please enter \'y\' or \'n\'");
//            answer = scanner.nextLine();
//        }
//        if (answer.equals("y")) {
//            Log.i("Please input the current JSON String");
//            oldGameJsonString = scanner.nextLine();
//            launchInfo = gson.fromJson(oldGameJsonString, LaunchInfo.class);
//        } else
        launchInfo = new LaunchInfo();

        //Set the format this Game is designed in. The class "Format" has static instances of the supported formats
        Log.i("Please enter the format of your game:");
        launchInfo.setFormat(scanner.nextLine());

        //Set the path of the executable related to the main directory of the repository
        // Example: <REPO>/out/Game.jar      Enter: "out/Game.jar"

        //Do NOT use the next method if the path is dependant of the OS it is running on
        Log.i("Does your game require different executables based on the current OS?");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            Log.i("Please enter \'y\' or \'n\'");
            answer = scanner.nextLine();
        }
        if (answer.equals("n")) {
            Log.i("Enter the executable path to be used relative the the main repository:");
            launchInfo.setExecutablePath(scanner.nextLine());
        } else {
            //If the executable path changes based on the OS it is running on enter the relative paths in to the osArray in
            // the same order as they are in the supported OS array
            // If the executable path is not dependant on the OS comment the following section out
            String[] executablePath = new String[osArray.size()];
            for (int x = 0; x < executablePath.length; x++) {
                Log.i("Please enter the path used by " + osArray.get(x));
                executablePath[x] = scanner.nextLine();
            }
            launchInfo.setOsSpecificExecutablePath(executablePath);
        }

        //Set the path of the library related to the main directory of the repository
        // Example: <REPO>/libs     Enter: "libs"
        Log.i("Does your game require special libraries to be specified?");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            Log.i("Please enter \'y\' or \'n\'");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")) {
            //Do NOT use the next method if the path is dependant of the OS it is running on
            Log.i("Does your game require different libraries based on the current OS?");
            answer = scanner.nextLine();
            while (!answer.equals("y") && !answer.equals("n")) {
                Log.i("Please enter \'y\' or \'n\'");
                answer = scanner.nextLine();
            }
            if (answer.equals("n")) {
                Log.i("Enter the library path to be used relative the the main repository:");
                launchInfo.setLibraryPath(scanner.nextLine());
            } else {
                String[] libraryPath = new String[osArray.size()];
                for (int x = 0; x < libraryPath.length; x++) {
                    Log.i("Please enter the path used by " + osArray.get(x));
                    libraryPath[x] = scanner.nextLine();
                }
                launchInfo.setOsSpecificLibraryPath(libraryPath);
            }
        }
        Log.i("\n");
        Log.i("Game JSON String:");
        Log.i(gson.toJson(game));

        Log.i("\nLaunchInfo JSON String:");
        Log.i(gson.toJson(launchInfo));

        Log.i("\nSend Game JSON String to Mocs-Arcade maintainer and request your Game be added or updated");
        Log.i("Place your LaunchInfo JSON String in \"launchInfo.json\" in the main directory of the repository branch" +
                " specified for your game");
    }
}
