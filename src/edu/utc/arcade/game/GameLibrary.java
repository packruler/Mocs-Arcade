package edu.utc.arcade.game;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */

//TODO define how the GameLibrary will handle loading and saving various game JSON strings
public class GameLibrary {
    private ArrayList<Game> gameList = new ArrayList<>();
    private static final String libraryPath = "./library";

    public GameLibrary() throws IOException {
        FileReader reader = new FileReader(libraryPath);
        Gson gson = new Gson();
        Game[] gameArray = gson.fromJson(reader, Game[].class);
    }
}
