package edu.utc.arcade.settings;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.utc.arcade.logging.Log;

import java.io.*;

/**
 * Created by Ethan Leisinger on 1/8/16.
 */
public class SettingsHandler {
    private static final File LOCAL_DIRECTORY = new File("./local");
    private static final File SETTINGS_FILE = new File("./local/settings.json");

    private Settings settings;

    public SettingsHandler() {
        //If local directory does not exist make it
        if (!LOCAL_DIRECTORY.exists())
            Log.i("Local directory created: " + LOCAL_DIRECTORY.mkdir());

        //If settings file does not exist make a new one
        if (SETTINGS_FILE.exists())
            load();
    }

    private void load() {
        Gson gson = new Gson();
        try {
            settings = gson.fromJson(new FileReader(SETTINGS_FILE), Settings.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            settings = new Settings();
        }
    }

    private boolean save() {
        if (!LOCAL_DIRECTORY.exists())
            Log.i("Local Directory Made: " + LOCAL_DIRECTORY.mkdir());

        try {
            if (!SETTINGS_FILE.exists())
                Log.i("Created Settings File: " + SETTINGS_FILE.createNewFile());

            Gson gson = new Gson();
            FileWriter writer = new FileWriter(SETTINGS_FILE);
            writer.write(gson.toJson(settings));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
