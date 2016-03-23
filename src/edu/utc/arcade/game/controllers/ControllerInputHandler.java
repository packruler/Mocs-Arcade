package edu.utc.arcade.game.controllers;


import net.java.games.input.Controller;

import java.awt.*;

/**
 * Created by Ethan Leisinger on 3/21/16.
 */
public class ControllerInputHandler {
    private static Robot ROBOT;

    private static Robot getRobot() throws AWTException {
        if (ROBOT == null)
            ROBOT = new Robot();

        return ROBOT;
    }

    private void listen(Controller controller) {
//        Thread
    }

    private static class ControllerThread {
        private Controller controller;
        private Thread thread;

        public ControllerThread(Controller controller) {
            this.controller = controller;
            thread = new Thread();

        }

        private Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                while (true){
//                    if (controller.getEventQueue())
//                }
            }
        };
    }
}
