package com.nickrexrode.logging;

import com.nickrexrode.internal.base.State;
import com.nickrexrode.loader.LoadingBarQueue;

public final class Logger implements State {
    private static Logger INSTANCE;

    private static Logger getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Logger();
        }
        return INSTANCE;
    }


    private LoadingBarQueue queue;

    public Logger() {
        this.load();
    }

    private LoadingBarQueue getLoadingBarQueue() {
        return this.queue;
    }

    public static void log(String message) {
        //Some display
    }

    public static void error(String message) {
        //Error popup
    }

    public static void loading(String message) {
        System.out.println(message);
        getInstance().getLoadingBarQueue().push(message);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean load() {
        this.queue = LoadingBarQueue.getInstance();
        return true;
    }

    @Override
    public boolean shutdown() {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }


}
