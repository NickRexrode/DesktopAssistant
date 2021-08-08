package com.nickrexrode.config;

import com.nickrexrode.exception.config.ConfigNotFoundException;
import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;
import com.nickrexrode.logging.Logger;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public final class ConfigManager implements State {

    private static ConfigManager INSTANCE;
    public static ConfigManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigManager();
        }
        return INSTANCE;
    }

    private final HashMap<String, Config> configs;

    public ConfigManager() {
        this.configs = new HashMap<>();
        this.load();
    }
    public Config getConfig(String name) {
        Config config = configs.get(name.toLowerCase());
        if (config == null) {
            System.out.println(name);
            throw new ConfigNotFoundException();
        }
        return config;
    }

    public Config getConfig(Application application) {
        Config config = configs.get(application.getName().toLowerCase());
        if (config == null) {
            System.out.println(application.getName());
            throw new ConfigNotFoundException();
        }
        return config;
    }

    public boolean addConfig(Config config) {
        try {
            this.configs.put(config.getPluginName().toLowerCase(), config);
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }



    @Override
    public boolean load() {
        Logger.loading("Loading configurations");
        List<File> files = FileManager.getAllFilesInDirectory(FileManager.CONFIG_DIRECTORY);

        for (int i = 0; i < files.size();i++) {
            Logger.loading("Loading configurations ("+(i+1)+"/"+files.size()+")");
            File file = files.get(i);
            Config config = ConfigFactory.loadConfiguration(file);
            this.configs.put(config.getPluginName().toLowerCase(), config);
        }
        return true;
    }

    @Override
    public boolean shutdown() {
        this.save();
        return true;
    }

    @Override
    public boolean save() {
        this.configs.forEach((name, config) -> {
            config.save();
        });
        return true;
    }
}
