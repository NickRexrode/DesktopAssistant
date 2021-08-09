package com.nickrexrode.base;

public interface State {
    boolean load();
    boolean shutdown();
    boolean save();
}
