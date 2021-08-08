package com.nickrexrode.external;

import java.io.File;

public abstract class CustomApplication extends Application{

    //Injected
    private File file;

    public CustomApplication(String name, String thumbnailLocation) {
        super(name, thumbnailLocation);
    }


    public void setFile(File file) {
        this.file = file;
    }

}
