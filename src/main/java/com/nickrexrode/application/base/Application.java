package com.nickrexrode.application.base;

import com.nickrexrode.config.ApplicationConfig;
import com.nickrexrode.config.base.Config;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.base.State;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Application implements Comparable<Application>, State{

    protected String name;
    protected String thumbnailLocation;

    public Application(String name, String thumbnailLocation) {
        this.name = name;
        this.thumbnailLocation = thumbnailLocation;
    }
    public boolean createDefaultConfigFile(File f) {

        boolean created= false;
        try {
            created = f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("application", this.name);
        Config config = new ApplicationConfig(this.name, map,f);

        ConfigManager.getInstance().addConfig(config);
        config.save();

        return created;
    }
    public String getName() {
        return this.name;
    }

    public String getThumbnailLocation() {
        return this.thumbnailLocation;
    }

    public abstract void execute();

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Application)) {
            return false;
        }
        Application o = (Application) other;
        return this.name.equals(o.getName());

    }

    @Override
    public int compareTo(Application other) {
        return this.getName().compareTo(other.getName());
    }

    public boolean runTerminalCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            process.destroy();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
