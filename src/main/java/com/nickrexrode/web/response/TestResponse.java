package com.nickrexrode.web.response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nickrexrode.web.TestClassData;

public class TestResponse extends Response<TestClassData> {

    /**
     * MUST CONVERT TO THE DATA CLASS RETURNED IN THIS METHOD
     * @param obj
     * @return
     */
    @Override
    public TestClassData from(JsonObject obj) {
        Gson gson = new Gson();
        TestClassData data = gson.fromJson(obj, TestClassData.class);
        return data;
    }
}
