package com.nickrexrode.internal.io;


import com.nickrexrode.config.ConfigManager;
import com.nickrexrode.internal.base.State;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileManager implements State {


    private static FileManager INSTANCE;

    public static final String HOME_DIRECTORY = System.getProperty("user.home") + File.separator + "DesktopAssistant";
    public static final String APPLICATION_DIRECTORY = HOME_DIRECTORY + File.separator + "applications";
    public static final String CONFIG_DIRECTORY = HOME_DIRECTORY + File.separator + "config";
    public static final String SCRIPT_DIRECTORY = HOME_DIRECTORY + File.separator + "scripts";


    public FileManager() {


        this.load();
    }

    public static FileManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FileManager();
        }
        return FileManager.INSTANCE;
    }

    @Override
    public boolean load() {
        if (!folderExists(HOME_DIRECTORY)) {
            this.makeFolder(HOME_DIRECTORY);
        }

        //Check applications, scripts, and config folders exist

        if (!folderExists(APPLICATION_DIRECTORY)) {
            this.makeFolder(APPLICATION_DIRECTORY);
        }
        if (!folderExists(CONFIG_DIRECTORY)) {
            this.makeFolder(CONFIG_DIRECTORY);
        }
        if (!folderExists(SCRIPT_DIRECTORY)) {
            this.makeFolder(SCRIPT_DIRECTORY);
        }


        return true;


        //Combine Classpaths
    }

    public boolean folderExists(String dir) {
        File file = new File(dir);
        return file.exists() && file.isDirectory();
    }


    public boolean makeFolder(String dir) {
        File file = new File(dir);
        return file.mkdir();
    }

    public static List<File> getAllFilesInDirectory(String dir) {
        List<File> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(dir))) {
            files = paths.filter(Files::isRegularFile)
                    .map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
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