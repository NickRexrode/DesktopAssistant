package com.nickrexrode.external;

import com.nickrexrode.internal.application.ApplicationManager;
import com.nickrexrode.internal.base.State;

import java.io.IOException;

public abstract class Application implements Comparable<Application> {

    protected String name;
    protected String thumbnailLocation;

    public Application(String name, String thumbnailLocation) {
        this.name = name;
        this.thumbnailLocation = thumbnailLocation;
    }

    public String getName() {
        return this.name;
    }

    public String getThumbnailLocation() {
        return this.thumbnailLocation;
    }


    public abstract void execute();


    @Override
    public boolean equals(Object other) {

        if (!(other instanceof Application)) {
            return false;
        }

        Application o = (Application) other;


        return this.name.equals(o.getName());

    }

    @Override
    public int compareTo(Application other) {
        return this.getName().compareTo(other.getName());
    }

    public boolean runTerminalCommand(String command) {

        String[] args = command.split(" ");

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            process.destroy();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
