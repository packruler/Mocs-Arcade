package edu.utc.arcade.settings;

import com.google.gson.JsonObject;
import edu.utc.arcade.logging.Log;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/8/16.
 */
public class SettingsHandler {
    private static final File LOCAL_DIRECTORY = new File("./local");
    private static final File SETTINGS_FILE = new File("./local/settings.json");

    private JsonObject values;

    private File standardGameDir;

    public SettingsHandler() throws IOException {
        //If local directory does not exist make it
        if (!LOCAL_DIRECTORY.exists())
            Log.i("Local directory created: " + LOCAL_DIRECTORY.mkdir());

        //If settings file does not exist make a new one
        if (!SETTINGS_FILE.exists())
            Log.i("Settings file created: " + SETTINGS_FILE.createNewFile());
        else
            load();
    }

    private void load() throws IOException {
//        JsonR
    }
}
