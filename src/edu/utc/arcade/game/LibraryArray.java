package edu.utc.arcade.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Nathan Neff on 1/22/16.
 */
public class LibraryArray {

    private ArrayList<Game> libraryList = new ArrayList<>();
//    private LinkedList<Game> updatedDataList = new LinkedList<>();

    /**
     * Add a Game to the library
     *
     * @param newGame The Game intended to be added to the library
     * @return true if an equivalent Game was found and data was updated
     */
    public boolean add(Game newGame) {
        int pos = libraryList.indexOf(newGame);
        if (pos != -1)
            return libraryList.get(pos).updateData(newGame);

        newGame.loadLaunchInfo();
        libraryList.add(newGame);
        return true;
    }

    /**
     * Add a Collection of Games to the library
     *
     * @param collection Collection of Games to be added to library
     * @return true if any duplicates were found and updated
     */
    public boolean addAll(Collection<Game> collection) {
        boolean updated = false;
        for (Game game : collection) {
            if (add(game) && !updated)
                updated = true;
        }
        return updated;
    }

    public Game get(int index) {
        return libraryList.get(index);
    }

    public List<Game> getList() {
        return libraryList;
    }

    /**
     * Get a list of Games in library
     *
     * @param localOnly If true only Games that are installed locally will be included in the List
     * @return List of Games in library
     */
    public List<Game> getList(boolean localOnly) {
        if (!localOnly)
            return getList();
        ArrayList<Game> out = new ArrayList<>();
        for (Game game : libraryList) {
            if (game.isLocal())
                out.add(game);
        }
        return out;
    }

    public Game remove(int index) {
        return libraryList.remove(index);
    }

    public boolean contains(Object o) {
        return libraryList.contains(o);
    }

//    public boolean updateGameData(Game current) {
//        Log.i("Update check");
//        for (Game updatedData : updatedDataList) {
//            if (updatedData.equals(current) &&
//                    (current.getDataUpdateTime() < updatedData.getDataUpdateTime())) {
//                current.updateData(updatedData);
//                return true;
//            }
//        }
//        return false;
//    }
}
