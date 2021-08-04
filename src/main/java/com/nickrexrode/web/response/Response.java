package com.nickrexrode.web.response;

import com.google.gson.JsonObject;

public abstract class Response<T> {
    public abstract T from(JsonObject obj);
}
