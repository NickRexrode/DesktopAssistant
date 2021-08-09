package com.nickrexrode.config.base;

import com.nickrexrode.exceptions.config.ConfigKeyNotFoundException;
import com.nickrexrode.base.State;
import java.util.Map;

public abstract class Config implements State {

    protected String pluginName;
    protected Map<String, Object> data;

    public Config(String pluginName, Map<String, Object> data) {
        this.pluginName = pluginName;
        this.data = data;
    }

    public String getPluginName() {
        return this.pluginName;
    }

    public Object get(String key) {
        Object obj =this.data.get(key);
        if (obj == null) {
            throw new ConfigKeyNotFoundException();
        }
        return obj;

    }

    public void set(String key, Object value) {
        this.data.replace(key, value);
    }
    @Override
    public abstract boolean load();

    @Override
    public abstract boolean shutdown();

    @Override
    public abstract boolean save();
}
