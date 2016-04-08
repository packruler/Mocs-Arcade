package edu.utc.arcade.git;

import edu.utc.arcade.logging.Log;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BranchTrackingStatus;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 1/23/16.
 */
public class SystemGitUpdater {

    public static int countBehind() {
        try {
            File directory = new File("");
            Git git = Git.open(directory);
            git.fetch().call();
            BranchTrackingStatus status = BranchTrackingStatus.of(git.getRepository(), git.getRepository().getBranch());
            return status.getBehindCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void update() {
        try {
            File directory = new File("");
            Git git = Git.open(directory);
            if (!git.pull().call().isSuccessful())
                return;
            File arcade = new File("Mocs-Arcade.jar");
            LinkedList<String> commands = new LinkedList<>();
            commands.add("java");
            commands.add("-jar");
            commands.add(arcade.getAbsolutePath());
            StringBuilder stringBuilder = new StringBuilder();
            for (String string : commands) {
                stringBuilder.append(string);
            }
            Log.i(stringBuilder.toString());
            String[] commandArray = new String[commands.size()];
            ProcessBuilder builder = new ProcessBuilder(commands.toArray(commandArray));
            Log.i(builder.toString());
            Process process = builder.start();
            Log.i(process.isAlive() ? "Alive" : "FAIL");
            System.exit(0);
        } catch (Exception e) {
            Log.e(e.getMessage());
        }
    }
}
