package edu.utc.arcade.settings;

/**
 * Created by Ethan Leisinger on 1/15/16.
 */
public class Settings {
    private boolean kioskMode;
    private String kioskPassword;

    public boolean isKioskMode() {
        return kioskMode;
    }

    public boolean enterKioskMode(String password) {
        if (!kioskMode) {
            kioskPassword = password;
            return true;
        }
        return false;
    }

    public boolean exitKioskMode(String password) {
        if (kioskMode && password.equals(kioskPassword))
            return true;
        return false;
    }

}
