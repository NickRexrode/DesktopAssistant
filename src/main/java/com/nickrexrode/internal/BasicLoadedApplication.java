package com.nickrexrode.internal;

import com.nickrexrode.DesktopAssistant;
import com.nickrexrode.config.ApplicationConfig;
import com.nickrexrode.config.Config;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.exception.config.ConfigNotFoundException;
import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BasicLoadedApplication extends Application implements State {

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


    private boolean createDefaultConfigFile(File f) {
        Yaml yaml = new Yaml();

        Map<String, Object> map = new HashMap<>();
        map.put("application", this.name);

        boolean created= false;
        FileWriter writer = null;

        try {
            created = f.createNewFile();
            writer = new FileWriter(f);
        } catch (IOException e) {
            e.printStackTrace();
            created = false;
        }
        String str = yaml.dump(map).replace("{", "").replace("}", "");
        System.out.println(str);
        try {
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return created;
    }
    @Override
    public boolean load() {

        try {
            this.config = ConfigManager.getInstance().getConfig(this.name);
        } catch (ConfigNotFoundException e) {
            createDefaultConfigFile(new File(FileManager.CONFIG_DIRECTORY+File.separator+this.name+"Default"+".yml"));
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
