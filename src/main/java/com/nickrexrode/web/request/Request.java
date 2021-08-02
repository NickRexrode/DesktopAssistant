package com.nickrexrode.web.request;

public abstract class Request<T> {
    protected String url;

    public Request(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
    public abstract Class<T> getResponseClass();
}
