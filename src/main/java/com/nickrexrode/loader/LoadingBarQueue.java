package com.nickrexrode.loader;

import com.nickrexrode.config.base.Config;
import com.nickrexrode.config.ConfigFactory;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.gui.loader.LoaderSplashScreen;
import com.nickrexrode.base.State;
import com.nickrexrode.io.FileManager;
import java.io.File;

public final class LoadingBarQueue extends CustomQueueADT<String> implements State {

    private static LoadingBarQueue INSTANCE;
    public static LoadingBarQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoadingBarQueue();
        }
        return INSTANCE;
    }

    private int count;
    private int lastCount;
    private LoaderSplashScreen loaderController;

    public LoadingBarQueue() {
        this.count = 0;
        this.lastCount = 0;
        this.load();
    }

    public int getLastCount() {
        return this.lastCount;
    }

    public void setLoaderController(LoaderSplashScreen loaderSplashScreen) {
        this.loaderController = loaderSplashScreen;
    }

    private void display(String str) {
        loaderController.setProgressBarStatus((double) count/lastCount);
        loaderController.setText(str);
        count++;
    }

    public void push(String str) {
        super.push(str);
        this.display(super.pop());
    }

    @Override
    public boolean load() {
        //Preload config before ConfigManager is loaded
        File file = new File(FileManager.CONFIG_DIRECTORY+File.separator+"loaderStorage.yml");
        Config config = ConfigFactory.loadConfiguration(file);
        this.lastCount = (Integer) config.get("count");
        return true;
    }

    @Override
    public boolean shutdown() {
        save();
        return true;
    }

    @Override
    public boolean save() {
        Config config = ConfigManager.getInstance().getConfig("loaderStorage");
        config.set("count", this.count);
        config.save();
        return true;
    }
}
