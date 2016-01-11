package edu.utc.arcade.game;

import com.google.gson.Gson;
import edu.utc.arcade.logging.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */

//TODO define how the GameLibrary will handle loading and saving various game JSON strings
public class GameLibrary {
    private ArrayList gameList = new ArrayList();
    private static final String libraryPath = "./Games/library";
    private static final File library = new File(libraryPath);

    public GameLibrary() {
        try {
            FileReader reader = new FileReader(library);
            Gson gson = new Gson();
            Game[] games = gson.fromJson(reader, Game[].class);
            gameList.addAll(Arrays.asList(games));
            Log.i("Library count: " + gameList.size());
        } catch (IOException e) {
            Log.i("No Library");
        }
    }

    public void saveGson() throws IOException {
        if (!library.exists())
            Log.i("New File created: " + library.createNewFile());

        FileWriter writer = new FileWriter(library);
        writer.write(new Gson().toJson(gameList.toArray()));
        writer.close();
    }

    public void addGame(Game game) {
        gameList.add(game);
    }
}
