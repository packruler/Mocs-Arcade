package edu.utc.arcade.game;

import com.google.gson.Gson;
import edu.utc.arcade.logging.Log;

import java.io.*;
import java.util.*;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */

//TODO define how the GameLibrary will handle loading and saving various game JSON strings
public class GameLibrary {
    private static final File LIBRARY_DIRECTORY = new File("./local/");
    private static final File LOCAL_LIBRARY = new File(LIBRARY_DIRECTORY.getPath() + "/library.json");
    private static final File REMOTE_LIBRARY = new File("./gameLibrary.json");

    //Store Games in TreeSet to allow them to be sorted
    //It also allows loading local and remote libraries without duplicates
    private TreeSet<Game> library = new TreeSet<>();

    public GameLibrary() {
        try {
            Gson gson = new Gson();

            //Load info for games that are stored locally
            FileReader reader = new FileReader(LOCAL_LIBRARY);
            Game[] gameArray = gson.fromJson(reader, Game[].class);
            library.addAll(Arrays.asList(gameArray));
            reader.close();

            //Load info for all games in remote library
            if (REMOTE_LIBRARY.exists()) {
                reader = new FileReader(REMOTE_LIBRARY);
                gameArray = gson.fromJson(reader, Game[].class);
                library.addAll(Arrays.asList(gameArray));
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGson() throws IOException {
        if (!LIBRARY_DIRECTORY.exists())
            Log.i("Game Directory Created" + LIBRARY_DIRECTORY.mkdir());

        if (!LOCAL_LIBRARY.exists())
            Log.i("New File created: " + LOCAL_LIBRARY.createNewFile());

        FileWriter writer = new FileWriter(LOCAL_LIBRARY);
        writer.write(new Gson().toJson(library.toArray()));
        writer.close();
    }

    public void addGame(Game game) {
        library.add(game);
    }

    public Set<Game> getLibrary() {
        return library;
    }
}
