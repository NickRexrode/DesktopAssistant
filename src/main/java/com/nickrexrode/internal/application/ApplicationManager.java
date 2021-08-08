package com.nickrexrode.internal.application;

import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;
import com.nickrexrode.logging.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public final class ApplicationManager implements State {
    private static ApplicationManager INSTANCE;

    private List<Application> customApplications;
    private List<Application> scriptApplications;


    public ApplicationManager() {
        customApplications = new ArrayList<>();
        scriptApplications = new ArrayList<>();
        load();
    }

    public static ApplicationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationManager();
        }
        return INSTANCE;
    }

    public List<Application> getAllApplications() {
        List<Application> fullList = new ArrayList<>();
        fullList.addAll(customApplications);
        fullList.addAll(scriptApplications);
        return fullList;
    }

    public List<Application> getCustomApplications() {
        return customApplications;
    }

    public List<Application> getScriptApplications() {
        return scriptApplications;
    }

    @Override
    public boolean load() {
        Logger.loading("Loading script applications");
        List<File> scriptFiles = FileManager.getAllFilesInDirectory(FileManager.SCRIPT_DIRECTORY);

        for (int i = 0; i < scriptFiles.size(); i++) {
            Logger.loading("Loading script applications (" + (i + 1) + "/" + scriptFiles.size() + ")");
            Application application = ApplicationFactory.loadApplication(scriptFiles.get(i));
            scriptApplications.add(application);
        }

        List<File> jarFiles = FileManager.getAllFilesInDirectory(FileManager.APPLICATION_DIRECTORY)
                .stream()
                .filter(file -> {
                            String extension = "";

                            int i = file.getName().lastIndexOf('.');
                            if (i > 0) {
                                extension = file.getName().substring(i + 1);
                            }

                            return extension.equals("jar");
                        }
                ).collect(Collectors.toList());


        Logger.loading("Loading custom applications");
        for (int i = 0; i < jarFiles.size(); i++) {
            Logger.loading("Loading custom applications ("+(i+1)+"/"+jarFiles.size()+")");

            JarFile jarFile = null;
            try {
                jarFile = new JarFile(jarFiles.get(i));

                JarEntry entry = jarFile.getJarEntry("application.yml");

                Yaml yaml = new Yaml();
                Map<String, Object> map = yaml.load(jarFile.getInputStream(entry));
                String main = map.get("main").toString();

                CustomApplicationClassLoader classLoader = new CustomApplicationClassLoader(jarFiles.get(i), this.getClass().getClassLoader());

                Class<?> jarClazz = classLoader.loadClass(main);

                Class<? extends Application> clazz = jarClazz.asSubclass(Application.class);


                this.customApplications.add(clazz.newInstance());

            } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
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
