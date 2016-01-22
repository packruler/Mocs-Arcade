package edu.utc.arcade.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan Leisinger on 1/22/16.
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
    }

    public Game get(int index) {
        return libraryList.get(index);
    }

    public List<Game> getList() {
        return libraryList;
    }

    public Game remove(int index) {
        return libraryList.remove(index);
    }

    public boolean contains(Object o) {
        return libraryList.contains(o);
    }
}
