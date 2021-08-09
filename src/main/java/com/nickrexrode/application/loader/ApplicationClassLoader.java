package com.nickrexrode.application.loader;

import com.nickrexrode.application.BasicLoadedApplication;
import com.nickrexrode.application.CustomApplication;
import com.nickrexrode.application.base.Application;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

public final class ApplicationClassLoader extends URLClassLoader {

    private CustomApplication application;
    public ApplicationClassLoader(File jarFile, ClassLoader parent) throws MalformedURLException {
        super(new URL[] {jarFile.toURI().toURL()}, parent);
    }

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



    public CustomApplication getApplication() {
        return this.application;
    }
}
