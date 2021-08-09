package com.nickrexrode.web.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestResponse extends Response<Object> {

    /**
     * MUST CONVERT TO THE DATA CLASS RETURNED IN THIS METHOD
     * @param obj
     * @return
     */
    @Override
    public Object from(JsonObject obj) {
        Gson gson = new Gson();
        Object data = gson.fromJson(obj, Object.class);
        return data;
    }
}
