package com.nickrexrode.internal;

import com.nickrexrode.DesktopAssistant;
import com.nickrexrode.config.ApplicationConfig;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public final class BasicLoadedApplication extends Application implements State {

    private ApplicationConfig config;
    private List<String> commands;

    public BasicLoadedApplication(String name, String thumbnailLocation, List<String> commands) {
        super(name, thumbnailLocation);
        this.commands = commands;
    }



    @Override
    public void execute() {
        for (int i = 0; i < commands.size(); i++) {
            this.runTerminalCommand(commands.get(i));
        }
    }

    @Override
    public boolean load() {
        this.config = ConfigManager.getInstance().getConfig(this.name);
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
