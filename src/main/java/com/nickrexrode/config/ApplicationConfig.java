package com.nickrexrode.config;

import com.nickrexrode.internal.base.State;

public final class ApplicationConfig implements State {

    private String pluginName;

    public ApplicationConfig(String pluginName) {
        this.pluginName = pluginName;
    }

    public String getPluginName() {
        return this.pluginName;
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
