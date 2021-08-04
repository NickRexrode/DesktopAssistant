package com.nickrexrode.config;

import com.nickrexrode.exception.config.ConfigNotFoundException;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public final class ConfigFactory {
    public static ApplicationConfig loadConfiguration(File file) {
        Yaml yaml = new Yaml();
        Map<String, Object> map;
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new ConfigNotFoundException();
        }
        return new ApplicationConfig((String) map.get("application"),map, file);
    }
}
