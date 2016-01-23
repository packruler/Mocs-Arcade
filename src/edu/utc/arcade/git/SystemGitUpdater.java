package edu.utc.arcade.git;

import edu.utc.arcade.logging.Log;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BranchTrackingStatus;

import java.io.File;

/**
 * Created by Ethan Leisinger on 1/23/16.
 */
public class SystemGitUpdater {

    public int countBehind() {
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
}
