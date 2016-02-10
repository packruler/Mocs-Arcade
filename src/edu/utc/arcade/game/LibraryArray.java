package edu.utc.arcade.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Nathan Neff on 1/22/16.
 */
public class LibraryArray {
    //TODO: Design an class that handles building a list of all Games.

    private ArrayList<Game> libraryList = new ArrayList<>();

    public void add(Game newGame) {
        /**
         * TODO: When adding a new Game to the array check the array for a Game in the array that equals the new Game
         * Game.equals(Game) will tell you if they are the same Game. If they are the same then check the dataUpdateTimes.
         * This will be a time that will be similar to System.currentTimeMillis(). If the remote time > local time run
         * LocalGame.dataUpdate(RemoteGame). I will design a data update method soon.
         */
        int pos = libraryList.indexOf(newGame);
        if (pos != -1)
            updateGame(libraryList.get(pos), newGame);
        else
            libraryList.add(newGame);

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

    public Game remove(int index) {
        return libraryList.remove(index);
    }

    public boolean contains(Object o) {
        return libraryList.contains(o);
    }

    public void updateGame(Game current, Game updatedData) {
        if (current.getDataUpdateTime() < updatedData.getDataUpdateTime())
            current.updateData(updatedData);
    }
}
