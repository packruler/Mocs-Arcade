package edu.utc.arcade.game;

import edu.utc.arcade.logging.Log;

import java.util.*;

/**
 * Created by Nathan Neff on 1/22/16.
 */
public class LibraryArray {

    private ArrayList<Game> libraryList = new ArrayList<>();

    public void add(Game newGame) {
        int pos = libraryList.indexOf(newGame);
        if (pos != -1)
            updateGame(libraryList.get(pos), newGame);
        else
            libraryList.add(newGame);
    }

    public void addAll(Collection<Game> collection) {
        for (Game game : collection) {
            add(game);
        }
    }

    public Game get(int index) {
        return libraryList.get(index);
    }

    public List<Game> getList() {
        return libraryList;
    }

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

    public Set<Game> getSet() {
        return new TreeSet<>(libraryList);
    }

    public Set<Game> getSet(boolean localOnly) {
        return new TreeSet<>(getList(localOnly));
    }

    public Game remove(int index) {
        return libraryList.remove(index);
    }

    public boolean contains(Object o) {
        return libraryList.contains(o);
    }

    private void updateGame(Game current, Game updatedData) {
        Log.i("Update check");
        if (current.getDataUpdateTime() < updatedData.getDataUpdateTime())
            current.updateData(updatedData);
    }
}
