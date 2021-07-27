package com.nickrexrode.internal.application;

import com.nickrexrode.external.Application;
import com.nickrexrode.internal.base.State;
import com.nickrexrode.internal.io.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ApplicationManager implements State {
    private static ApplicationManager INSTANCE;

    private List<Application> customApplications;
    private List<Application> scriptApplications;


    public ApplicationManager() {
        customApplications = new ArrayList<>();
        scriptApplications = new ArrayList<>();
        load();
    }
    public static ApplicationManager getInstance(){
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

        List<File> scriptFiles = FileManager.getAllFilesInDirectory(FileManager.SCRIPT_DIRECTORY);

        for (int i = 0; i < scriptFiles.size(); i++) {
            Application application = ApplicationFactory.loadApplication(scriptFiles.get(i));
            scriptApplications.add(application);
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
