package edu.utc.arcade.game;

import com.google.gson.Gson;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */

//TODO define how the GameLibrary will handle loading and saving various game JSON strings
public class GameLibrary {
    public static final File LIBRARY_DIRECTORY = new File("./local/");
    private static final File LOCAL_LIBRARY = new File(LIBRARY_DIRECTORY.getPath() + "/library.json");
    private static final File REMOTE_LIBRARY = new File("./gameLibrary.json");
    private static GameLibrary INSTANCE;

    /**
     * Retrieve an instance of GameLibrary. If there is not an instance created a new one will be created
     *
     * @return instance of GameLibrary
     */
    public static GameLibrary getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GameLibrary();
        return INSTANCE;
    }

    //Store Games in TreeSet to allow them to be sorted
    //It also allows loading local and remote libraries without duplicates
    private LibraryArray library = new LibraryArray();

    private GameLibrary() {
        try {
            Gson gson = new Gson();

            FileReader reader;
            Game[] gameArray;

            //Load info for games that are stored locally
            if (LOCAL_LIBRARY.exists()) {
                reader = new FileReader(LOCAL_LIBRARY);
                gameArray = gson.fromJson(reader, Game[].class);
                library.addAll(Arrays.asList(gameArray));
                reader.close();
            }

            //Load info for all games in remote library
            if (REMOTE_LIBRARY.exists()) {
                reader = new FileReader(REMOTE_LIBRARY);
                gameArray = gson.fromJson(reader, Game[].class);
                boolean updatedGames = library.addAll(Arrays.asList(gameArray));
                reader.close();
                if (updatedGames)
                    saveGson();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Store the current library data to ./local/library.json
     *
     * @throws IOException
     */
    public void saveGson() throws IOException {
        if (!LIBRARY_DIRECTORY.exists())
            Log.i("Game Directory Created" + LIBRARY_DIRECTORY.mkdir());

        if (!LOCAL_LIBRARY.exists())
            Log.i("New File created: " + LOCAL_LIBRARY.createNewFile());

        FileWriter writer = new FileWriter(LOCAL_LIBRARY);
        writer.write(new Gson().toJson(library.getList().toArray()));
        writer.close();
    }

    /**
     * Add a Game to the library. If a game with the same name from the same developer is already in the library check
     * check to see if that Game data needs to be updated
     *
     * @param game
     */
    public void addGame(Game game) {
        library.add(game);
    }

    /**
     * Get the current library from a static state
     *
     * @return List of Games in the library instance.
     */
    public static List<Game> getLibrary() {
        return getInstance().getLibraryList();
    }

    public List<Game> getLibraryList() {
        List<Game> list = new LinkedList<>();
        for (Game game : library.getList()) {
            if (OSCheck.isCompatible(game))
                if (Settings.isKioskMode()) {
                    if (game.isLocal())
                        list.add(game);
                } else
                    list.add(game);
        }
        return list;
    }

    public Game getGame(String title, String developer) {
        for (Game cur : library.getList()) {
            if (cur.getTitle().equals(title)
                    && cur.getDeveloper().equals(developer))
                return cur;
        }
        return null;
    }
}
