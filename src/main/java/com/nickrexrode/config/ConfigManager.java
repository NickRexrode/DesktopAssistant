package com.nickrexrode.config;

import com.nickrexrode.exception.ConfigNotFoundException;
import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;

import java.io.ObjectInputFilter;
import java.util.HashMap;

public final class ConfigManager implements State {

    private static ConfigManager INSTANCE;
    public static ConfigManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigManager();
        }
        return INSTANCE;
    }

    private HashMap<String, ApplicationConfig> configs;

    public ApplicationConfig getConfig(String name) {

        ApplicationConfig config = configs.get(name);

        if (config == null) {
            throw new ConfigNotFoundException();
        }

        return config;
    }

    public ApplicationConfig getConfig(Application application) {

        ApplicationConfig config = configs.get(application.getName());

        if (config == null) {
            throw new ConfigNotFoundException();
        }
        return config;
    }



    @Override
    public boolean load() {
        return false;
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
