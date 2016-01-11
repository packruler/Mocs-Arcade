package edu.utc.arcade.settings;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import edu.utc.arcade.logging.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/8/16.
 */
public class Settings {

    private JsonObject values;
    private File settingsFile = new File("./settings.json");

    private File standardGameDir;

    public Settings() throws IOException {

        //If settings file does not exist make a new one
        if (!settingsFile.exists())
            Log.out("Settings file created: " + settingsFile.createNewFile());
        else
            load();
    }

    private void load() throws IOException{
//        JsonR
    }
}
