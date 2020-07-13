package red.felnull.ikisugibot;

import red.felnull.ikisugibot.handler.TimeHandler;

public class LoopThread extends Thread {

    public void run() {
        while (true) {
            try {
                sleep(1);
                TimeHandler.onTime();
            } catch (Exception e) {
            }
        }
    }
}
