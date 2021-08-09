package com.nickrexrode.config;

import com.nickrexrode.config.base.Config;
import com.nickrexrode.exceptions.config.ConfigNotFoundException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public final class ApplicationConfig extends Config {

    private final File file;

    public ApplicationConfig(String pluginName, Map<String, Object> data, File file) {
        super(pluginName, data);
        this.file = file;
    }

    @Override
    public boolean load() {
        return false;
    }

    @Override
    public boolean shutdown() {
        save();
        return true;
    }

    @Override
    public boolean save() {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        FileWriter writer;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new ConfigNotFoundException();
        }

        String str = yaml.dump(data);

        try {
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
