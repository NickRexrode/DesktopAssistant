package com.nickrexrode.internal.base;

public interface State {
    boolean load();
    boolean shutdown();
    boolean save();
}
