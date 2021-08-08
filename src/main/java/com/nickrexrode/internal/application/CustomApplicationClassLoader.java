package com.nickrexrode.internal.application;

import com.nickrexrode.external.Application;
import com.nickrexrode.external.CustomApplication;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public final class CustomApplicationClassLoader extends URLClassLoader {

    private CustomApplication application;
    public CustomApplicationClassLoader(File jarFile, ClassLoader parent) throws MalformedURLException {
        super(new URL[] {jarFile.toURI().toURL()}, parent);


    }

    public CustomApplication getApplication() {
        return this.application;
    }
}
