package com.nickrexrode.web;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nickrexrode.web.request.Request;
import com.nickrexrode.web.response.Response;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public final class RequestManager {
    private static RequestManager INSTANCE;

    public static RequestManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestManager();
        }
        return INSTANCE;
    }


    public <R, T extends Response<R>> R execute(Request<T> request) {
        Class<T> responseClass = request.getResponseClass();
        T res = null;
        try {
            res = responseClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        JsonObject jsonObject = null;
        try {

            jsonObject = doHTTPSRequest(request.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject == null) {
            throw new NullPointerException("JSON was null");
        }



        return res.from(jsonObject);
    }
    private JsonObject doHTTPSRequest(String urlString) throws Exception{

        URL url = new URL(urlString);

        URLConnection con = url.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        InputStream response = con.getInputStream();

        String result = new BufferedReader(new InputStreamReader(response)).lines().collect(Collectors.joining("\n"));
        System.out.println(result);
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setInstanceFollowRedirects(false);
//
//        System.out.println(con.getResponseCode());
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//        String inputLine;
//        StringBuilder content = new StringBuilder();
//        while((inputLine = in.readLine()) !=null) {
//            content.append(inputLine);
//        }
//
//        in.close();
//        con.disconnect();
//        String jsonString = content.toString();
//
//        System.out.println(jsonString);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(result, JsonElement.class).getAsJsonObject();


        return jsonObject;
    }

}
