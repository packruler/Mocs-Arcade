package edu.utc.arcade.game;

import com.google.gson.Gson;
import edu.utc.arcade.logging.Log;
import edu.utc.arcade.settings.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */

//TODO define how the GameLibrary will handle loading and saving various game JSON strings
public class GameLibrary {
    public static final File LIBRARY_DIRECTORY = new File("./local/");
    private static final File LOCAL_LIBRARY = new File(LIBRARY_DIRECTORY.getPath() + "/library.json");
    private static final File REMOTE_LIBRARY = new File("./gameLibrary.json");
    private static GameLibrary INSTANCE;

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

    public void saveGson() throws IOException {
        if (!LIBRARY_DIRECTORY.exists())
            Log.i("Game Directory Created" + LIBRARY_DIRECTORY.mkdir());

        if (!LOCAL_LIBRARY.exists())
            Log.i("New File created: " + LOCAL_LIBRARY.createNewFile());

        FileWriter writer = new FileWriter(LOCAL_LIBRARY);
        writer.write(new Gson().toJson(library.getList().toArray()));
        writer.close();
    }

    public void addGame(Game game) {
        library.add(game);
    }

    public Set<Game> getLibrary() {
        return new TreeSet<>(getLibraryList());
    }

    public List<Game> getLibraryList() {
        if (!Settings.getInstance().isKioskMode())
            return library.getList();

        List<Game> list = new LinkedList<>();
        for (Game game : library.getList()) {
            if (game.isLocal())
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
