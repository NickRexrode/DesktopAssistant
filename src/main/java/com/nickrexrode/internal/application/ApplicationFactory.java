package com.nickrexrode.internal.application;

import com.nickrexrode.external.Application;
import com.nickrexrode.internal.BasicLoadedApplication;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public final class ApplicationFactory {

    public static Application loadApplication(File file) {
        Yaml yaml = new Yaml();
        Map<String, Object> map = null;
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String name = (String) map.get("name");
        String thumbnailLocation = (String) map.get("thumbnailLocation");
        List<String> commands = (List<String>) (map.get("commands"));
        return new BasicLoadedApplication(name, thumbnailLocation, commands);

    }

}
