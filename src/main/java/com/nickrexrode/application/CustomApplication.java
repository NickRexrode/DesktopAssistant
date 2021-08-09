package com.nickrexrode.application;

import com.nickrexrode.application.base.Application;
import com.nickrexrode.config.base.Config;
import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.exceptions.config.ConfigNotFoundException;
import com.nickrexrode.io.FileManager;
import com.nickrexrode.logging.Logger;

import java.io.File;

public abstract class CustomApplication extends Application {

    //Injected

    private Config config;

    public CustomApplication(String name, String thumbnailLocation) {
        super(name, thumbnailLocation);
    }

    @Override
    public boolean load() {
        try {

            this.config = ConfigManager.getInstance().getConfig(this.name);

        } catch (ConfigNotFoundException e) {
            Logger.loading("No config found for: "+this.name);
            this.createDefaultConfigFile(new File(FileManager.CONFIG_DIRECTORY+File.separator+this.name+"Default"+".yml"));
            Logger.loading("Created default config for "+this.name+" at\n"+FileManager.CONFIG_DIRECTORY+File.separator+this.name+"Default"+".yml");
            this.config = ConfigManager.getInstance().getConfig(this.name);


        }
        return true;
    }
}
