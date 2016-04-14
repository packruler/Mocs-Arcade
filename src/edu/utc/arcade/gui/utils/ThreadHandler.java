package edu.utc.arcade.gui.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ethan Leisinger on 4/13/16.
 */
public class ThreadHandler {
    private static ThreadHandler INSTANCE;
    private LinkedBlockingQueue queue = new LinkedBlockingQueue();
    private ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 4, 1, TimeUnit.MINUTES, queue);

    private ThreadHandler() {

    }

    public static ThreadHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ThreadHandler();

        return INSTANCE;
    }

    public static void run(Runnable runnable) {
        getInstance().pool.execute(runnable);
    }

    public static BlockingQueue getQueue() {
        return getInstance().queue;
    }
}
