package edu.utc.arcade.git;

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
public class GameGitHandler {
    /**
     * Clone remote repo for Game to local directory.
     * Directory will be ./local/<i>Developer</i>/<i>Title</i>
     *
     * @param game Game to be cloned to local directory
     * @return True if cloned successfully
     */
    public static boolean clone(Game game) {
        Log.i("Clone: " + game);
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
            //Set Game 'local' value to true indicating that the game is available locally
            game.setLocal(true);
            return true;
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Pull remote repo to local directory
     *
     * @param game Game to be updated
     * @return true if the pull completed successfully.
     * Possible no updates were made if local repo was already up to date
     */
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

    /**
     * Get number of commits behind the remote repo the local directory is
     *
     * @param game Game that will be checked on
     * @return number of commits behind remote repo the local repo is.
     * will return -1 if any errors occur
     */
    public static int countBehind(Game game) {
        File directory = new File(GameLibrary.LIBRARY_DIRECTORY.getPath()
                + "/" + game.getDeveloper()
                + "/" + game.getTitle());
        if (!directory.exists())
            return -1;

        try {
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
