package edu.utc.arcade.logging;

/**
 * Created by Ethan Leisinger on 1/5/2016.
 */
public class Log {
    public static void i(String msg) {
        System.out.println(msg);
    }

    public static void e(String msg) {
        System.err.println(msg);
    }
}
