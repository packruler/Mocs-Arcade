package edu.utc.arcade;

import com.sun.org.apache.regexp.internal.RE;
import edu.utc.arcade.game.Game;
import edu.utc.arcade.game.GameLibrary;
import edu.utc.arcade.logging.Log;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.BranchTrackingStatus;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.merge.MergeConfig;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Ethan Leisinger on 1/8/16.
 */
public class GitHandler {

    public static boolean clone(Game game) {
        File directory = new File(GameLibrary.LIBRARY_DIRECTORY.getPath()
                + "/" + game.getDeveloper()
                + "/" + game.getTitle());
        if (directory.exists())
            Log.i("Delete directory: " + directory.delete());

        Log.i("Directories made: " + directory.mkdirs());
        try {
            Git result = Git.cloneRepository()
                    .setDirectory(directory)
                    .setBranch(game.getGitBranch())
                    .setURI(game.getGitAddress())
                    .call();
            Log.i("Cloned");
            game.setLocal(true);
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean pull(Game game) {
        File directory = new File(GameLibrary.LIBRARY_DIRECTORY.getPath()
                + "/" + game.getDeveloper()
                + "/" + game.getTitle());
        if (!directory.exists())
            return false;

        try {
            Git git = Git.open(directory);
            return git.pull().call().isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int countBehind(Game game) {
        File directory = new File(GameLibrary.LIBRARY_DIRECTORY.getPath()
                + "/" + game.getDeveloper()
                + "/" + game.getTitle());
        if (!directory.exists())
            return -1;

        try {
            Git git = Git.open(directory);
//            Collection<Ref> refs = git.fetch().call().getAdvertisedRefs();
            BranchTrackingStatus status = BranchTrackingStatus.of(git.getRepository(), git.getRepository().getBranch());
            return status.getBehindCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
