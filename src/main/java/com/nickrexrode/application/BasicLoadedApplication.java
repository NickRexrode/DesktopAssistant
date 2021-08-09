package com.nickrexrode.application;

import com.nickrexrode.config.base.Config;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.exceptions.config.ConfigNotFoundException;
import com.nickrexrode.application.base.Application;
import com.nickrexrode.io.FileManager;

import java.io.*;
import java.util.List;

public final class BasicLoadedApplication extends Application {

    private Config config;
    private List<String> commands;

    public BasicLoadedApplication(String name, String thumbnailLocation, List<String> commands) {
        super(name, thumbnailLocation);
        this.commands = commands;
        load();
    }



    @Override
    public void execute() {
        for (int i = 0; i < commands.size(); i++) {
            this.runTerminalCommand(commands.get(i));
        }
    }



    @Override
    public boolean load() {
        try {
            this.config = ConfigManager.getInstance().getConfig(this.name);
        } catch (ConfigNotFoundException e) {
            this.createDefaultConfigFile(new File(FileManager.CONFIG_DIRECTORY+File.separator+this.name+"Default"+".yml"));
            this.config = ConfigManager.getInstance().getConfig(this.name);
        }
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
