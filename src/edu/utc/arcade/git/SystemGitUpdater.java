package edu.utc.arcade.git;

import edu.utc.arcade.gui.MainMenu;
import edu.utc.arcade.logging.Log;
import javafx.scene.control.Button;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BranchTrackingStatus;
import org.eclipse.jgit.merge.MergeStrategy;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by Ethan Leisinger on 1/23/16.
 */
public class SystemGitUpdater {

    public static boolean needUpdate() {
        return countBehind() > 0;
    }

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
            git.pull().setStrategy(MergeStrategy.THEIRS);
            if (!git.pull().call().isSuccessful()) {
                ((Button) MainMenu.getScene().lookup("#updateButton")).setText("ERROR");
                return;
            }
            File arcade = new File("Mocs-Arcade.jar");
            LinkedList<String> commands = new LinkedList<>();
            commands.add("java");
            commands.add("-jar");
            commands.add(arcade.getAbsolutePath());
            String[] commandArray = new String[commands.size()];
            ProcessBuilder builder = new ProcessBuilder(commands.toArray(commandArray));
            Process process = builder.start();
            Log.i(process.isAlive() ? "Alive" : "FAIL");
            System.exit(0);
        } catch (Exception e) {
            Log.e(e.getMessage());
        }
    }
}
