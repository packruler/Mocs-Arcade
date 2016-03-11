package edu.utc.arcade.settings;

import com.google.gson.Gson;
import edu.utc.arcade.logging.Log;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ethan Leisinger on 1/15/16.
 */
public class Settings {
    private static Settings INSTANCE;
    private static final File LOCAL_DIRECTORY = new File("./local");
    private static final File SETTINGS_FILE = new File("./local/settings.json");

    private boolean inKioskMode = false;
    private String kioskPassword = "";


    public static Settings getInstance() {
        if (INSTANCE == null) {
            try {
                Gson gson = new Gson();
                if (!SETTINGS_FILE.exists()) {
                    INSTANCE = new Settings();
                    INSTANCE.save();
                } else {
                    INSTANCE = gson.fromJson(new FileReader(SETTINGS_FILE), Settings.class);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return INSTANCE;
    }

    public boolean save() {
        if (!LOCAL_DIRECTORY.exists())
            Log.i("Local Directory Made: " + LOCAL_DIRECTORY.mkdir());

        try {
            if (!SETTINGS_FILE.exists())
                Log.i("Created Settings File: " + SETTINGS_FILE.createNewFile());

            Gson gson = new Gson();
            FileWriter writer = new FileWriter(SETTINGS_FILE);
            writer.write(gson.toJson(this));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isKioskMode() {
        return inKioskMode;
    }

    public boolean enterKioskMode(String password) {
        if (!inKioskMode) {
            kioskPassword = password;
            inKioskMode = true;
        }
        save();
        return inKioskMode;
    }

    public boolean exitKioskMode(String password) {
        if (inKioskMode && password.equals(kioskPassword))
            inKioskMode = false;
        save();
        return !inKioskMode;
    }
}
