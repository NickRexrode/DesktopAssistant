package com.nickrexrode.loader;

import com.nickrexrode.DesktopAssistant;
import com.nickrexrode.config.Config;
import com.nickrexrode.config.ConfigFactory;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.exception.config.ConfigNotFoundException;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Paths;

public final class LoadingBarQueue extends CustomQueueADT<String> implements State {




    private static LoadingBarQueue INSTANCE;

    private int count;
    private int lastCount;
    private ProgressBar progressBar;
    public static LoadingBarQueue getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoadingBarQueue();
        }
        return INSTANCE;
    }

    //temp

    public int getLastCount() {
        return this.lastCount;
    }



    public LoadingBarQueue() {
        this.count = 0;
        this.lastCount = 0;
        this.load();
    }
    public void setLoadingBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    private void display() {
        String str = this.pop();

        progressBar.setProgress(count);
        count++;
        //TODO: adjust text
        //adjust text

    }

    public void push(String str) {
        super.push(str);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.display();
    }


    @Override
    public boolean load() {


        System.out.println("Loading bar Loaded");
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
        config.save();
        return true;
    }
}
