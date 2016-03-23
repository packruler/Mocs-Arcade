package edu.utc.arcade;

import edu.utc.arcade.game.LaunchInfo;
import edu.utc.arcade.logging.Log;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Ethan Leisinger on 3/23/16.
 */
public class LaunchInfoCreator {
    public static void main(String[] args) {
        LaunchInfo launchInfo = new LaunchInfo();
        Scanner scanner = new Scanner(System.in);


        String answer = "";
        Log.i("What is the format of your game?");
        Log.i("(Supported formats: Jar, C, C++)");
        answer = scanner.nextLine();
        while (!answer.equalsIgnoreCase("Jar") && !answer.equalsIgnoreCase("C") && !answer.equalsIgnoreCase("C++")) {
            Log.i("Entered format not supported. Please enter one of the following: Jar, C, C++");
            answer = scanner.nextLine();
        }
        launchInfo.setFormat(answer);

        Log.i("Does your game have Operating System specific executable paths? (y/n)");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            Log.i("Please enter \'n\' or \'y\'");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")) {
            LinkedList<String> paths = new LinkedList<>();
            Log.i("Please enter the first path of executable relative to repository: (Use path for Operating System in postion 0 of supported Operating Systems)");
            Log.i("Example:");
            Log.i("If Location: <REPOSITORY DIRECTORY>/game.jar");
            Log.i("Enter: \"game.jar\"");
            paths.add(scanner.nextLine());

            while (answer.equals("y")) {
                Log.i("Please enter the path of the executable relative to position " + (paths.size()));
                paths.add(scanner.nextLine());
                Log.i("Are there any more paths to add?");
                answer = scanner.nextLine();
                while (!answer.equals("y") && !answer.equals("n")) {
                    Log.i("Please enter \'n\' or \'y\'");
                    answer = scanner.nextLine();
                }
            }
            String[] pathArray = new String[paths.size()];
            paths.toArray(pathArray);
            launchInfo.setOsSpecificExecutablePath(pathArray);
        } else {
            Log.i("What is the path of executable relative to repository?");
            Log.i("Example:");
            Log.i("If Location: <REPOSITORY DIRECTORY>/game.jar");
            Log.i("Enter: \"game.jar\"");
            launchInfo.setExecutablePath(scanner.next());
            Log.i("Entered path: \"" + launchInfo.getExecutablePath() + "\"");
        }

        Log.i("Does your game require a library included in the repository? (y/n)");
        answer = scanner.nextLine();
        while (!answer.equals("y") && !answer.equals("n")) {
            Log.i("Please enter \'n\' or \'y\'");
            answer = scanner.nextLine();
        }
        if (answer.equals("y")) {
            LinkedList<String> paths = new LinkedList<>();
            Log.i("Please enter the first path of library relative to repository: (Use path for Operating System in postion 0 of supported Operating Systems)");
            Log.i("Example:");
            Log.i("If Location: <REPOSITORY DIRECTORY>/game.jar");
            Log.i("Enter: \"game.jar\"");
            paths.add(scanner.nextLine());

            while (answer.equals("y")) {
                Log.i("Please enter the path of the library relative to position " + (paths.size()));
                paths.add(scanner.nextLine());
                Log.i("Are there any more libraries to add?");
                answer = scanner.nextLine();
                while (!answer.equals("y") && !answer.equals("n")) {
                    Log.i("Please enter \'n\' or \'y\'");
                    answer = scanner.nextLine();
                }
            }
            String[] pathArray = new String[paths.size()];
            paths.toArray(pathArray);
            launchInfo.setOsSpecificExecutablePath(pathArray);
        } else {
            Log.i("What is the path of library relative to repository?");
            launchInfo.setExecutablePath(scanner.next());
        }
    }
}
